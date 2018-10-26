package DDS.SGE.Persistencia;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente;
import DDS.SGE.EntityManagerHelper;
import DDS.SGE.RepositorioDeTiempoEncendidoTest;
import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.Estado.Apagado;
import Fabricante.AireAcondicionado;
import Geoposicionamiento.Transformador;

public class TestDispositivo {

	Cliente clienteSinDispositivos;
	Cliente clienteConUnDispositivoEstandar;
	Cliente clienteConUnDispositivoInteligente;
	Dispositivo dispositivoSencillo;
	Dispositivo dispositivoInteligente;
	DispositivoEstandar estandar = new DispositivoEstandar(10, 20);
	DispositivoInteligente inteligente;
	Transformador unTransformador = new Transformador(200);
	
	LocalDateTime fechaDeReferencia = LocalDateTime.now();
	IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
	IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5),
			fechaDeReferencia.minusHours(3));
	List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
	RepositorioDeTiempoEncendidoTest repositorioDePrueba = new RepositorioDeTiempoEncendidoTest(intervalosDeActividad);
	
	@Before
	public void Inicializar() {

		inteligente = new DispositivoInteligente(new Apagado(), new AireAcondicionado(3800));
		dispositivoSencillo = new Dispositivo(estandar);
		dispositivoSencillo.setNombre("Sencillo");

		dispositivoInteligente = new Dispositivo(inteligente);

		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList());

		clienteConUnDispositivoEstandar = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoSencillo));

		clienteConUnDispositivoInteligente = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoInteligente));
		
		unTransformador.agregarCliente(clienteConUnDispositivoInteligente);

		EntityManagerHelper.beginTransaction();
	}
	
	@After
	public void after() {
		EntityManagerHelper.rollback();
	}

	@Test
	public void PersistirUnUsuarioYLuegoCambiarleGeolocalizacion() {
		EntityManager em = EntityManagerHelper.entityManager();

		em.persist(clienteSinDispositivos);

		Cliente clientePersistido = em.find(Cliente.class, clienteSinDispositivos.getId());

		clientePersistido.setDomicilio("calle x");

		em.persist(clientePersistido);

		Cliente clienteActualizado = em.find(Cliente.class, clienteSinDispositivos.getId());

		assertEquals("calle x", clienteActualizado.getDomicilio());
	}

	@Test
	public void PersistirUnDispositivoEstandarYLuegoLevantarlo() {
		EntityManager em = EntityManagerHelper.entityManager();

		em.persist(clienteConUnDispositivoEstandar);
		em.persist(dispositivoSencillo);
		em.persist(estandar);

		Dispositivo dispositivoPersistido = em.find(Dispositivo.class, dispositivoSencillo.getId());
		dispositivoPersistido.setNombre("Sencillamente actualizado");
		em.persist(dispositivoPersistido);

		Dispositivo dispositivoActualizado = em.find(Dispositivo.class, dispositivoSencillo.getId());
		assertEquals("Sencillamente actualizado", dispositivoActualizado.getNombre());
	}

	@Test
	public void PersistirUnDispositivoInteligenteYLuegoLevantarlo() {

		EntityManager em = EntityManagerHelper.entityManager();

		em.persist(clienteConUnDispositivoInteligente);
		em.persist(dispositivoInteligente);
		em.persist(inteligente);

		Dispositivo dispositivoPersistido = em.find(Dispositivo.class, dispositivoInteligente.getId());
		dispositivoPersistido.setNombre("Sencillamente actualizado");
		em.persist(dispositivoPersistido);

		Dispositivo dispositivoActualizado = em.find(Dispositivo.class, dispositivoPersistido.getId());
		assertEquals("Sencillamente actualizado", dispositivoActualizado.getNombre());
	}

	@Test
	public void PersistirUnDispositivoInteligenteYLuegoLevantarloConSuRepositorioDeIntervalosActualizado() {

		EntityManager em = EntityManagerHelper.entityManager();

		em.persist(clienteConUnDispositivoInteligente);
		em.persist(inteligente);
		em.persist(dispositivoInteligente);
				
		Dispositivo dispositivoPersistido = em.find(Dispositivo.class, dispositivoInteligente.getId());

		DispositivoInteligente dispositivoInteligentePersistido = (DispositivoInteligente) dispositivoPersistido
				.getTipoDispositivo();

		assertEquals(0, dispositivoPersistido.consumoTotalHaceNHoras(100), 0);

		dispositivoInteligentePersistido.setRepositorio(repositorioDePrueba);

		Dispositivo dispositivoPersistidoActual = em.find(Dispositivo.class, dispositivoPersistido.getId());

		DispositivoInteligente dispositivoInteligentePersistidoActual = (DispositivoInteligente) dispositivoPersistidoActual
				.getTipoDispositivo();

		dispositivoInteligentePersistidoActual.getRepositorioTiempoEncendido().getIntervalosDeActividad()
				.forEach(intervalo -> System.out.println(intervalo.getIntervaloEncendidoEnMinutos()));

		assertEquals(180 * inteligente.getConsumoKWPorHora(), dispositivoPersistidoActual.consumoTotalHaceNHoras(100),
				0);
	}
	
	@Test
	public void RecuperarDispositivoAsociadoAUnHogarEIncrementarConsumo() {
		EntityManager em = EntityManagerHelper.entityManager();

		em.persist(clienteConUnDispositivoInteligente);
		em.persist(dispositivoInteligente);
		em.persist(inteligente);
		em.persist(unTransformador);

		assertEquals(10, unTransformador.suministra(), 0.0);

		Transformador t = em.find(Transformador.class, unTransformador.getId());
		List<Cliente> usuarios = t.getUsuarios();
		Cliente unCliente = usuarios.get(0);
		List<Dispositivo> dispositivos = (List<Dispositivo>) unCliente.getDispositivos();
		Dispositivo unDisp = dispositivos.get(0);

		Dispositivo dispPersistido = em.find(Dispositivo.class, unDisp.getId());
		// TODO Aca habria que aumentar el consumo

		em.persist(dispPersistido);

		// TODO: Habria que ver si se modifica el consumo?
		assertEquals(20, unTransformador.suministra(), 0.0);

		EntityManagerHelper.rollback();
	}

}
