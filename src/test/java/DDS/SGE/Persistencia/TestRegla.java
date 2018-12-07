package DDS.SGE.Persistencia;

import DDS.SGE.Dispositivo.*;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Fabricante.AireAcondicionado;
import DDS.SGE.Geoposicionamiento.Transformador;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Dispositivos.RepositorioDeTiempoEncendidoTest;

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
        //dispositivoSencillo.setNombre("Sencillo");

        dispositivoInteligente = new Dispositivo(inteligente);
    }

    @After
    public void after() {

    }

    @Test
    public void PersistirUnaReglaModificarlaYLuegoLevantarla() {
		/*
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
	*/
    }
}
