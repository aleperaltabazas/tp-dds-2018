package DDS.SGE;

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
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Actuador.Actuador;
import DDS.SGE.Actuador.Apagar;
import DDS.SGE.Cliente.TipoDni;

public class TestPersistencia {

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

	Transformador transformador_1 = new Transformador(1);
	Transformador transformador_2 = new Transformador(2);
	Transformador transformador_3 = new Transformador(3);
	Transformador transformador_4 = new Transformador(4);

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
		em.flush();
		em.persist(dispositivoInteligente);

		em.flush();
		
		
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

	@Test
	public void PersistirCorrectamenteLosTransformadores() {
		EntityManager em = EntityManagerHelper.entityManager();
		transformador_1.agregarCliente(clienteSinDispositivos);

		em.persist(clienteSinDispositivos);

		em.persist(transformador_1);
		em.persist(transformador_2);
		em.persist(transformador_3);

		TypedQuery<Long> query1 = em.createQuery("SELECT COUNT(t.id) FROM Transformador t", Long.class);
		Long cantidadDeTransformadoresPersistidosVersion1 = query1.getSingleResult();

		em.persist(transformador_4);
		TypedQuery<Long> query2 = em.createQuery("SELECT COUNT(t.id) FROM Transformador t", Long.class);
		Long cantidadDeTransformadoresPersistidosVersion2 = query2.getSingleResult();

		int a = cantidadDeTransformadoresPersistidosVersion1.intValue();
		int b = cantidadDeTransformadoresPersistidosVersion2.intValue();

		assertEquals(a + 1, b);
	}

}
