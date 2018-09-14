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
	DispositivoInteligente inteligente = new DispositivoInteligente(new Apagado(), new AireAcondicionado(3800));
	
	LocalDateTime fechaDeReferencia = LocalDateTime.now();
	IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
	IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5), fechaDeReferencia.minusHours(3));
	List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
	RepositorioDeTiempoEncendidoTest repositorioDePrueba = new RepositorioDeTiempoEncendidoTest(intervalosDeActividad);
	
	Transformador transformador_1 = new Transformador(1);
	Transformador transformador_2 = new Transformador(2);
	Transformador transformador_3 = new Transformador(3);
	Transformador transformador_4 = new Transformador(4);

	@Before
	public void Inicializar() {
		
		dispositivoSencillo = new Dispositivo(estandar);
		dispositivoSencillo.setNombre("Sencillo");
		
		dispositivoInteligente = new Dispositivo(inteligente);
		
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList());
		
		clienteConUnDispositivoEstandar = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoSencillo));
		
		clienteConUnDispositivoInteligente = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoInteligente));
		
		
		
		EntityManagerHelper.beginTransaction();
	}

	@After
	public void after() {
		EntityManagerHelper.rollback();
	}

	@Test
	public void PersistirUnUsuarioYLuegoCambiarleGeolocalizacion() {
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

		Dispositivo dispositivoActualizado = em.find(Dispositivo.class, dispositivoInteligente.getId());
		assertEquals("Sencillamente actualizado", dispositivoActualizado.getNombre());

	}
	
	@Test
	public void PersistirUnDispositivoInteligenteYLuegoLevantarloConSuRepositorioDeIntervalosActualizado() {
		
		EntityManager em = EntityManagerHelper.entityManager();
				
		em.persist(clienteConUnDispositivoInteligente);
		em.persist(dispositivoInteligente);
		em.persist(inteligente);
		
		Dispositivo dispositivoPersistido = em.find(Dispositivo.class, dispositivoInteligente.getId());
		
		DispositivoInteligente dispositivoInteligentePersistido = (DispositivoInteligente) dispositivoPersistido.getTipoDispositivo();
				
		dispositivoInteligentePersistido.getRepositorioTiempoEncendido().getIntervalosDeActividad().forEach(intervalo -> System.out.println(intervalo.getTiempoInicial()));
		
		assertEquals(0, dispositivoPersistido.consumoTotalHaceNHoras(100), 0);

		dispositivoInteligentePersistido.setRepositorio(repositorioDePrueba);
		
		em.persist(dispositivoInteligentePersistido);
		em.persist(dispositivoPersistido);
		
		Dispositivo dispositivoActualizado = em.find(Dispositivo.class, dispositivoInteligente.getId());
		
		DispositivoInteligente dispositivoInteligentePersistidoActual = (DispositivoInteligente) dispositivoPersistido.getTipoDispositivo();

		dispositivoInteligentePersistidoActual.getRepositorioTiempoEncendido().getIntervalosDeActividad().forEach(intervalo -> System.out.println(intervalo.getTiempoInicial()));
		
		assertEquals(180 * inteligente.getConsumoKWPorHora(), dispositivoActualizado.consumoTotalHaceNHoras(100), 0);
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
	public void PersistirCorrectamenteLosTransformadores() {
		EntityManager em = EntityManagerHelper.entityManager();
		
		em.persist(transformador_1);
		em.persist(transformador_2);
		em.persist(transformador_3);
		
		TypedQuery<Transformador> query1 = 
				em.createQuery("SELECT t FROM Transformador t", Transformador.class);
		List<Transformador> transformadoresPersistidosVersion1 = query1.getResultList();
		int cantidadDeTransformadoresPersistidosVersion1 = transformadoresPersistidosVersion1.size();
		
		em.persist(transformador_4);
		TypedQuery<Transformador> query2 = 
				em.createQuery("SELECT t FROM Transformador t", Transformador.class);
		List<Transformador> transformadoresPersistidosVersion2 = query2.getResultList();
		int cantidadDeTransformadoresPersistidosVersion2 = transformadoresPersistidosVersion2.size();
		
		assertEquals(cantidadDeTransformadoresPersistidosVersion1 + 1 , cantidadDeTransformadoresPersistidosVersion2);


	}
}
