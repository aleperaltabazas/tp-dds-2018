package DDS.SGE.Persistencia;

import DDS.SGE.Dispositivo.*;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Regla.Regla;
import DDS.SGE.Sensor.Consumo;
import DDS.SGE.Sensor.Sensor;
import DDS.SGE.Sensor.Temperatura;
import Fabricante.AireAcondicionado;
import Geoposicionamiento.Transformador;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.EntityManagerHelper;
import DDS.SGE.RepositorioDeTiempoEncendidoTest;
import DDS.SGE.Actuador.Actuador;
import DDS.SGE.Actuador.Apagar;

public class TestRegla {

	Dispositivo dispositivoSencillo;
	Dispositivo dispositivoInteligente;
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
		dispositivoSencillo.setNombre("Sencillo");

		dispositivoInteligente = new Dispositivo(inteligente);

		EntityManagerHelper.beginTransaction();
	}
	
	@After
	public void after() {
		EntityManagerHelper.rollback();
	}

	@Test
	public void PersistirUnaReglaModificarlaYLuegoLevantarla() {

		EntityManager em = EntityManagerHelper.entityManager();

		Actuador apagar = new Apagar(inteligente);
		Sensor consumo = new Consumo(dispositivoInteligente);

		Regla reglaDelSimplex = new Regla(Arrays.asList(consumo), apagar);

		em.persist(reglaDelSimplex);

		Regla reglaPersistida = em.find(Regla.class, reglaDelSimplex.getId());

		assertEquals(apagar, reglaPersistida.getActuador());
		assertEquals(consumo, reglaPersistida.getSensores().get(0));

		Sensor otraCondicion = new Temperatura(22, inteligente);

		List<Sensor> nuevasCondiciones = Arrays.asList(consumo, otraCondicion);
		reglaPersistida.setSensores(nuevasCondiciones);

		em.persist(reglaPersistida);

		Regla reglaActualizada = em.find(Regla.class, reglaPersistida.getId());

		assertEquals(nuevasCondiciones, reglaActualizada.getSensores());

	}
}
