package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;
import Geoposicionamiento.Zona;

public class TestOptimizador {

	private static final double USO_MENSUAL_RECOMENDADO = 612.0;

	Computadora unFabricante = new Computadora(true);

	Zona unaZona = new Zona();

	DispositivoInteligente dispositivoInfractorInteligente = new DispositivoInteligente(new Encendido());

	Dispositivo dispositivoPotencia1Kw = new Dispositivo(1, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoPotencia2Kw = new Dispositivo(2, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoPotencia3Kw = new Dispositivo(3, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoPotenciaMuyAlta = new Dispositivo(10000, new DispositivoInteligente(new Encendido()),
			unFabricante);
	Dispositivo otroDispositivoPotencia2Kw = new Dispositivo(2, new DispositivoInteligente(new Encendido()),
			unFabricante);
	Dispositivo dispositivoInfractor = new Dispositivo(2, dispositivoInfractorInteligente, unFabricante);

	Cliente clienteSinDispositivos;
	Cliente clienteConDispositivoDe1Kw;
	Cliente clienteConDispositivoDe2Kw;
	Cliente clienteConDispositivoDeMuchaPotencia;
	Cliente clienteCon2DispositivosDe2Kw;
	Cliente clienteConVariosDispositivos;
	Cliente clienteConDispositivoInfractor;

	LocalDateTime fechaDeReferencia = LocalDateTime.now();
	IntervaloActivo intervaloDe600Horas = new IntervaloActivo(fechaDeReferencia.minusHours(600), fechaDeReferencia);
	List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe600Horas);
	RepositorioDeTiempoEncendidoTest repositorioDeMuchoTiempoEncendido = new RepositorioDeTiempoEncendidoTest(
			intervalosDeActividad);

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

		clienteConDispositivoInfractor = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoInfractor));

		dispositivoInfractorInteligente.setRepositorio(repositorioDeMuchoTiempoEncendido);

	}

	@Test
	public void unClienteConDispositivoDePotencia2KwSeRecomiendaUsarloLaMitadDelUsoRecomendadoHoras() {

		assertEquals(USO_MENSUAL_RECOMENDADO / 2, Optimizador.Calcular(clienteConDispositivoDe2Kw), 0.0);

	}

	@Test
	public void ElUnicoDispositivoDePotencia2KwDelClienteSeRecomiendaUsarloLaMitadDelTiempoRecomendado() {

		double tiempoTotalDeUso = Optimizador.Calcular(clienteConDispositivoDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);

	}

	@Test
	public void unClienteCon2DispositivosDePotencia2KwSeRecomiendaUsarLaMismaCantidadDeHorasTotalesQueSiFueraUnoSolo() {

		assertEquals(Optimizador.Calcular(clienteConDispositivoDe2Kw),
				Optimizador.Calcular(clienteCon2DispositivosDe2Kw), 0.0);

	}

	@Test
	public void laSumaDelTiempoRecomendadoDeCadaDispositivoEsIgualAlTiempoTotalQueElClientePuedeUsarlos() {

		double tiempoTotalDeUso = Optimizador.Calcular(clienteCon2DispositivosDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar()
				+ otroDispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);

	}

	@Test
	public void unClienteConUnaComputadoraDePotencia1KwSeRecomiendaUsarLoMaximoPosible() {

		assertEquals(dispositivoPotencia1Kw.getFabricante().usoMensualMaximo(),
				Optimizador.Calcular(clienteConDispositivoDe1Kw), 0.0);

	}

	@Test
	public void unClienteSinDispositivosSeLeRecomiendaUsarLosDispositivosPor0Horas() {

		assertEquals(0, Optimizador.Calcular(clienteSinDispositivos), 0.0);

	}

	@Test
	public void unClientePuedePedirSuUsoRecomendado() {

		assertEquals(432, clienteConVariosDispositivos.consultarUsoOptimo(), 0.0);

	}

	@Test
	public void aUnClienteNoSeLeApagaSuDispositivoPorqueNoTuvoConsumo() {

		Optimizador.Calcular(clienteConDispositivoDe1Kw);
		assertTrue(clienteConDispositivoDe1Kw.getDispositivos().findFirst().get().estaEncendido());

	}

	@Test
	public void aUnClienteSeLeApagaSuDispositivoPorqueTuvoConsumoExcesivo() {

		Optimizador.Calcular(clienteConDispositivoInfractor);
		Optimizador.accionarSobreDispositivosInfractores(
				Optimizador.obtenerDispositivosInfractores(clienteConDispositivoInfractor.getDispositivos()));

		assertFalse(clienteConDispositivoInfractor.getDispositivos().findFirst().get().estaEncendido());

	}

}