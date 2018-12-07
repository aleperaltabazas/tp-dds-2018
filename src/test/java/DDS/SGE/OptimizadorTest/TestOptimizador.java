package DDS.SGE.OptimizadorTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.Dispositivos.FabricanteTest;
import DDS.SGE.Dispositivos.RepositorioDeTiempoEncendidoTest;
import DDS.SGE.Optimizador;
import DDS.SGE.Usuarie.Cliente;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Usuarie.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Geoposicionamiento.Zona;

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
	List<IntervaloActivo> intervalosDeActividad = new ArrayList<IntervaloActivo>(Arrays.asList(intervaloDe600Horas));
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

		assertEquals(USO_MENSUAL_RECOMENDADO / 2, optimizador.usoMensualRecomendado(clienteConDispositivoDe2Kw), 0.0);

	}

	@Test
	public void ElUnicoDispositivoDePotencia2KwDelClienteSeRecomiendaUsarloLaMitadDelTiempoRecomendado() {

		double tiempoTotalDeUso = optimizador.usoMensualRecomendado(clienteCon2DispositivosDe2Kw);
		optimizador.accionarSobreCliente(clienteConDispositivoDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);
	}

	@Test
	public void unClienteCon2DispositivosDePotencia2KwSeRecomiendaUsarLaMismaCantidadDeHorasTotalesQueSiFueraUnoSolo() {

		assertEquals(optimizador.usoMensualRecomendado(clienteConDispositivoDe2Kw),
				optimizador.usoMensualRecomendado(clienteCon2DispositivosDe2Kw), 0.0);
	}

	@Test
	public void laSumaDelTiempoRecomendadoDeCadaDispositivoEsIgualAlTiempoTotalQueElClientePuedeUsarlos() {

		double tiempoTotalDeUso = optimizador.usoMensualRecomendado(clienteCon2DispositivosDe2Kw);
		optimizador.accionarSobreCliente(clienteCon2DispositivosDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar()
				+ otroDispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);
	}

	@Test
	public void unClienteConUnaComputadoraDePotencia1KwSeRecomiendaUsarLoMaximoPosible() {

		assertEquals(dispositivoPotencia1Kw.getUsoMensualMaximo(), optimizador.usoMensualRecomendado(clienteConDispositivoDe1Kw), 0.0);

	}

	@Test
	public void unClienteSinDispositivosSeLeRecomiendaUsarLosDispositivosPor0Horas() {

		assertEquals(0, optimizador.usoMensualRecomendado(clienteSinDispositivos), 0.0);

	}

	@Test
	public void unClienteSoloConDispositivosEstandarSeLeRecomiendaUsarLosDispositivosPor0Horas() {

		assertEquals(0, optimizador.usoMensualRecomendado(clienteSoloConDispositivosEstandar), 0.0);

	}

	@Test
	public void unClientePuedePedirSuUsoRecomendado() {

		assertEquals(432, clienteConVariosDispositivos.consultarUsoOptimo(), 0.0);

	}

	@Test
	public void aUnClienteNoSeLeApagaSuDispositivoPorqueNoTuvoConsumo() {

		optimizador.accionarSobreCliente(clienteConDispositivoDe1Kw);
		assertTrue(clienteConDispositivoDe1Kw.getDispositivos().findFirst().get().estaEncendido());

	}

	@Test
	public void aUnClienteSeLeApagaSuDispositivoPorqueTuvoConsumoExcesivo() {

		optimizador.accionarSobreCliente(clienteConDispositivoInfractor);

		assertFalse(clienteConDispositivoInfractor.getDispositivos().findFirst().get().estaEncendido());

	}

	@Test
	public void unClienteConDIsTieneLaMismaRecomendacionQueOtroClienteConLosMismosDIsYVariosDispositivosEstandarAdicionales() {

		double tiempoClienteConSoloDIs = optimizador.usoMensualRecomendado(clienteConDispositivoInfractor);
		double tiempoClienteConAmbosTiposDeDispositivos = optimizador
				.usoMensualRecomendado(clienteConDispositivoInfractorYDispositivosAdicionales);

		assertEquals(tiempoClienteConSoloDIs, tiempoClienteConAmbosTiposDeDispositivos, 0);
	}

}