package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;
import Geoposicionamiento.Zona;

public class TestOptimizador {

	private static final double USO_MENSUAL_RECOMENDADO = 612.0;

	Zona unaZona = new Zona();

	DispositivoInteligente dispositivoInfractorInteligente = new DispositivoInteligente(new Encendido(), new FabricanteTest(2));

	Dispositivo dispositivoPotencia1Kw = new Dispositivo(
			new DispositivoInteligente(new Encendido(), new FabricanteTest(1)));
	Dispositivo dispositivoPotencia2Kw = new Dispositivo(
			new DispositivoInteligente(new Encendido(), new FabricanteTest(2)));
	Dispositivo dispositivoPotencia3Kw = new Dispositivo(
			new DispositivoInteligente(new Encendido(), new FabricanteTest(3)));
	Dispositivo dispositivoPotenciaMuyAlta = new Dispositivo(
			new DispositivoInteligente(new Encendido(), new FabricanteTest(10000)));
	Dispositivo otroDispositivoPotencia2Kw = new Dispositivo(
			new DispositivoInteligente(new Encendido(), new FabricanteTest(2)));
	Dispositivo dispositivoInfractor = new Dispositivo(dispositivoInfractorInteligente);
	Dispositivo dispositivoEstandar = new Dispositivo(new DispositivoEstandar(10, 2));

	Cliente clienteSinDispositivos;
	Cliente clienteConDispositivoDe1Kw;
	Cliente clienteConDispositivoDe2Kw;
	Cliente clienteConDispositivoDeMuchaPotencia;
	Cliente clienteCon2DispositivosDe2Kw;
	Cliente clienteConVariosDispositivos;
	Cliente clienteSoloConDispositivosEstandar;
	Cliente clienteConDispositivoInfractor;
	Cliente clienteConDispositivoInfractorYDispositivosAdicionales;

	LocalDateTime fechaDeReferencia = LocalDateTime.now();
	IntervaloActivo intervaloDe600Horas = new IntervaloActivo(fechaDeReferencia.minusHours(600), fechaDeReferencia);
	List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe600Horas);
	RepositorioDeTiempoEncendidoTest repositorioDeMuchoTiempoEncendido = new RepositorioDeTiempoEncendidoTest(
			intervalosDeActividad);

	Optimizador optimizador = new Optimizador();

	@Before
	public void initialize() {

		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList());

		clienteConDispositivoDe1Kw = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoPotencia1Kw));

		clienteConDispositivoDe2Kw = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotencia2Kw));

		clienteConDispositivoDeMuchaPotencia = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotenciaMuyAlta));

		clienteCon2DispositivosDe2Kw = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(),
				Arrays.asList(dispositivoPotencia2Kw, otroDispositivoPotencia2Kw));

		clienteConVariosDispositivos = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(),
				Arrays.asList(dispositivoPotencia2Kw, dispositivoPotencia1Kw, dispositivoPotencia3Kw));

		clienteSoloConDispositivosEstandar = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(),
				Arrays.asList(dispositivoEstandar, dispositivoEstandar, dispositivoEstandar));

		clienteConDispositivoInfractor = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoInfractor));

		clienteConDispositivoInfractorYDispositivosAdicionales = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321",
				"1188884444", "Calle Falsa 123", LocalDateTime.now(),
				Arrays.asList(dispositivoInfractor, dispositivoEstandar));

		dispositivoInfractorInteligente.setRepositorio(repositorioDeMuchoTiempoEncendido);

	}

	@Test
	public void unClienteConDispositivoDePotencia2KwSeRecomiendaUsarloLaMitadDelUsoRecomendadoHoras() {

		assertEquals(USO_MENSUAL_RECOMENDADO / 2, optimizador.Calcular(clienteConDispositivoDe2Kw), 0.0);

	}

	@Test
	public void ElUnicoDispositivoDePotencia2KwDelClienteSeRecomiendaUsarloLaMitadDelTiempoRecomendado() {

		double tiempoTotalDeUso = optimizador.Calcular(clienteConDispositivoDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);

	}

	@Test
	public void unClienteCon2DispositivosDePotencia2KwSeRecomiendaUsarLaMismaCantidadDeHorasTotalesQueSiFueraUnoSolo() {

		assertEquals(optimizador.Calcular(clienteConDispositivoDe2Kw),
				optimizador.Calcular(clienteCon2DispositivosDe2Kw), 0.0);

	}

	@Test
	public void laSumaDelTiempoRecomendadoDeCadaDispositivoEsIgualAlTiempoTotalQueElClientePuedeUsarlos() {

		double tiempoTotalDeUso = optimizador.Calcular(clienteCon2DispositivosDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar()
				+ otroDispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);

	}

	@Test
	public void unClienteConUnaComputadoraDePotencia1KwSeRecomiendaUsarLoMaximoPosible() {

		assertEquals(dispositivoPotencia1Kw.usoMensualMaximo(), optimizador.Calcular(clienteConDispositivoDe1Kw), 0.0);

	}

	@Test
	public void unClienteSinDispositivosSeLeRecomiendaUsarLosDispositivosPor0Horas() {

		assertEquals(0, optimizador.Calcular(clienteSinDispositivos), 0.0);

	}

	@Test
	public void unClienteSoloConDispositivosEstandarSeLeRecomiendaUsarLosDispositivosPor0Horas() {

		assertEquals(0, optimizador.Calcular(clienteSoloConDispositivosEstandar), 0.0);

	}

	@Test
	public void unClientePuedePedirSuUsoRecomendado() {

		assertEquals(432, clienteConVariosDispositivos.consultarUsoOptimo(), 0.0);

	}

	@Test
	public void aUnClienteNoSeLeApagaSuDispositivoPorqueNoTuvoConsumo() {

		optimizador.Calcular(clienteConDispositivoDe1Kw);
		assertTrue(clienteConDispositivoDe1Kw.getDispositivos().findFirst().get().estaEncendido());

	}

	@Test
	public void aUnClienteSeLeApagaSuDispositivoPorqueTuvoConsumoExcesivo() {

		optimizador.Calcular(clienteConDispositivoInfractor);
		optimizador.accionarSobreDispositivos(
				optimizador.obtenerDispositivosInfractores(clienteConDispositivoInfractor.getDispositivos()));

		assertFalse(clienteConDispositivoInfractor.getDispositivos().findFirst().get().estaEncendido());

	}

	@Test
	public void unClienteConDIsTieneLaMismaRecomendacionQueOtroClienteConLosMismosDIsYVariosDispositivosEstandarAdicionales() {

		double tiempoClienteConSoloDIs = optimizador.Calcular(clienteConDispositivoInfractor);
		double tiempoClienteConAmbosTiposDeDispositivos = optimizador
				.Calcular(clienteConDispositivoInfractorYDispositivosAdicionales);

		assertEquals(tiempoClienteConSoloDIs, tiempoClienteConAmbosTiposDeDispositivos, 0);
	}

}