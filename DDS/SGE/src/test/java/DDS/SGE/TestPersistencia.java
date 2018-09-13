package DDS.SGE;

import DDS.SGE.Dispositivo.*;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;

public class TestPersistencia {

	Cliente clienteSinDispositivos;
	Dispositivo dispositivoSencillo;
	DispositivoEstandar estandar = new DispositivoEstandar(10, 20);

	@Before
	public void Inicializar() {
		EntityManagerHelper.beginTransaction();
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList());

		dispositivoSencillo = new Dispositivo(estandar);
		dispositivoSencillo.setNombre("Sencillo");
	}

	@After
	public void after() {
		EntityManagerHelper.rollback();
	}

	@Test
	public void PersistirUusuarioYLuegoCambiarleGeolocalizacion() {
		// TODO: Ahora se estaria cambiando el domicilio, la idea es cambiarle la geo
		EntityManagerHelper.entityManager().persist(clienteSinDispositivos);
		Cliente clientePersistido = EntityManagerHelper.entityManager().find(Cliente.class,
				clienteSinDispositivos.getId());
		clientePersistido.setDomicilio("calle x");
		EntityManagerHelper.entityManager().persist(clientePersistido);
		Cliente clienteActualizado = EntityManagerHelper.entityManager().find(Cliente.class,
				clienteSinDispositivos.getId());
		assertEquals("calle x", clienteActualizado.getDomicilio());
	}

	@Test
	public void PersistirUnDispositivoYLuegoLevantarlo() {
		EntityManager em = EntityManagerHelper.entityManager();
		em.persist(estandar);
		em.persist(dispositivoSencillo);

		Dispositivo dispositivoPersistido = em.find(Dispositivo.class, dispositivoSencillo.getId());
		dispositivoPersistido.setNombre("Sencillamente actualizado");
		em.persist(dispositivoPersistido);

		Dispositivo dispositivoActualizado = em.find(Dispositivo.class, dispositivoSencillo.getId());
		assertEquals("Sencillamente actualizado", dispositivoActualizado.getNombre());

	}
}
