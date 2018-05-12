package DDS.SGE;

import static org.junit.Assert.*;
import com.google.gson.*;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import junit.framework.Assert;

public class SGETest {

	// Hago algunas instancias para los casos de prueba
	int diasDelMes = LocalDate.now().lengthOfMonth();
	String jsonDisp = "{nombre:dispositivo x,consumoKWPorHora:12,encendido:true}";
	String jsonCliente = "{nombre:gonzalo,apellido:vaquero,tipoDni:dni,numeroDni:\"1254156153\",telefono:\"4444444\",domicilio:calle falsa 123,fechaAltaServicio:\"20/05/2017\"}";
	String jsonAdmin = "{nombre:mati,apellido:giorda,domicilio:calle falsa 321, fechaAlta:\"20/15/2030\",identificador:2}";
	Dispositivo nintendoDS = new Dispositivo(0.5, false);
	Dispositivo televisor = new Dispositivo(1, false);
	Dispositivo plasma = new Dispositivo(2, false);
	Dispositivo computadora = new Dispositivo(1.2, true);
	Dispositivo estufaElectrica = new Dispositivo(1.2, false);
	Dispositivo pcGamer = new Dispositivo(0.21, true);
	// No estoy muy seguro si conviene usar listas de esta manera
	List<Dispositivo> dispositivosDeAlejandro = Arrays.asList(nintendoDS, plasma);
	List<Dispositivo> dispositivosDeLucila = Arrays.asList(televisor, computadora);
	List<Dispositivo> dispositivosDeMaxi = Arrays.asList(pcGamer);
	Cliente alejandro = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
			"Av siempre viva 742", LocalDateTime.now(), dispositivosDeAlejandro);
	Cliente lucila = new Cliente("Lucila", "Salmeron", TipoDni.DNI, "123456789", "1144448888", "Av siempre viva 742",
			LocalDateTime.now(), dispositivosDeLucila);
	Cliente maxi = new Cliente("Maxi", "Paz", TipoDni.DNI, "987654321", "1144448888", "Baker St. 221b", LocalDateTime.now(),
			dispositivosDeMaxi);
	LocalDateTime fechaDeAltaDeMarco = LocalDateTime.of(2017, 3, 20,15,15);
	Administrador marco = new Administrador("Marco", "Polo", "Rivadavia 1100", fechaDeAltaDeMarco, 42);

	// Al trabajar con el LocalDate.now() se nos genera el problema que dependiendo
	// del mes que lo probemos puedan correr bien los test y en otros casos no, hay
	// que estar atentos a eso


	@Test
	public void testAlejandroNoTieneNingunDispositivoEncendido() {
		assertFalse(alejandro.algunDispositivoEncendido());
	}

	@Test
	public void testLucilaTieneAlgunDispositivoEncendido() {
		assertTrue(lucila.algunDispositivoEncendido());
	}

	@Test
	public void testLucilaTiene1DispositivoEncendido() {
		assertEquals(1, lucila.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void testLucilaTiene2Dispositivos() {
		assertEquals(2, lucila.cantidadDispositivos());
	}

	@Test
	public void testLucilaTiene1DispositivoApagado() {
		assertEquals(1, lucila.cantidadDispositivosApagados());
	}

	@Test
	public void testAlEncenderseLosDispositivosDeAlejandroSuConsumoPorHoraEsLaSumaDelConsumoPorHoraDeAmbosDispositivos() {
		plasma.encender();
		nintendoDS.encender();

		assertEquals(plasma.getConsumoKWPorHora() + nintendoDS.getConsumoKWPorHora(),
				alejandro.consumoTotalEstimadoPorHora(), 0);
	}

	@Test
	public void testElConsumoMensualDeLucilaEsElCorrespondienteAlMesActual() {
		assertEquals((computadora.getConsumoKWPorHora() + televisor.getConsumoKWPorHora()) * 24 * diasDelMes,
				lucila.consumoTotalPorMes(), 0);
	}

	/*
	 * @Test public void
	 * testLaCategoriaDeMaxiEnCualquierMesQueNoSeaFebreroEsR2MientrasQueEnFebreroEsR1
	 * () { maxi.categorizar(); if (diasDelMes > 29) { assertEquals(Categoria.R2,
	 * maxi.getCategoria()); } else assertEquals(Categoria.R1, maxi.getCategoria());
	 * }
	 */

	@Test
	public void testLaCategoriaDeLucilaEsR9TrasCategorizarla() {
		// El consumo de lucila minimo en febrero de 28 dias es 2.2 * 24 * 28 = 1478 por
		// lo que es independiente del mes (siempre va a ser categoria 9)
		lucila.recategorizar();

		assertEquals(Categoria.R9, lucila.getCategoria());
	}

	@Test
	public void testLaFacturacionEstimadaVariableDeLucilaEsCorrespondienteALaCategoriaR9() {
		assertEquals(lucila.consumoTotalEstimadoPorHora() * Categoria.R9.getNormalVariable(),
				lucila.getCategoria().estimarFacturacionCargoVariable(lucila), 0);
	}

	@Test
	public void testRecategorizarAR8AAlejandro() {
		List<Dispositivo> dispositivosNuevos = Arrays.asList(estufaElectrica);
		alejandro.setDispositivos(dispositivosNuevos);

		// El rango de consumo de la estufa electrica va desde 1.2 * 24 * 28 = 806,4
		// hasta 1.2 * 24 * 31 = 892,8 por lo que queda en la categoria 8
		// independientemente del mes
		assertEquals(Categoria.R8, alejandro.getCategoria());
	}

	@Test
	public void testAlejandroPrendeYApagaLaNintendoDSYElPlasmaYAdquiereUnaComputadoraYOtroPlasmaYLosApagaYNoTieneDispositivosEncendidos() {
		nintendoDS.encender();
		nintendoDS.apagar();
		plasma.encender();
		plasma.apagar();

		Dispositivo nuevoPlasma = new Dispositivo(10, true);
		nuevoPlasma.apagar();
		computadora.apagar();

		List<Dispositivo> nuevosDispositivos = Arrays.asList(nintendoDS, plasma, nuevoPlasma, computadora);
		alejandro.setDispositivos(nuevosDispositivos);

		assertFalse(alejandro.algunDispositivoEncendido());

	}
	
	@Test
	public void testAlejandroTiene3NintendoDSYSuConsumoPorHoraEsElTripleQueUnaDS() {
		List<Dispositivo> nuevosDispositivos = Arrays.asList(nintendoDS, nintendoDS, nintendoDS);
		alejandro.setDispositivos(nuevosDispositivos);

		assertEquals(nintendoDS.getConsumoKWPorHora() * 3, alejandro.consumoTotalEstimadoPorHora(), 0);
	}

	@Test
	public void testEnAbrilElConsumoTotalPorMesDeLucilaEsMayorQueEnFebrero() {
		// El test tiene que ser durable, no deberia estarse usando el .now() porque si
		// lo pruebo en febrero este test fallaria
		LocalDate abril = LocalDate.of(2018, 4, 22);
		LocalDate febrero = LocalDate.of(2018, 2, 27);

		assertTrue(lucila.consumoTotalDeUnMesEspecifico(abril) > lucila.consumoTotalDeUnMesEspecifico(febrero));
	}

	// Creo que querian que usemos JodaTime, esto es mucho mas simple con esto:
	// https://www.leveluplunch.com/java/examples/number-of-months-between-two-dates/
	// -> Segundo ejemplo de la pagina
	/*
	 * @Test public void
	 * testMarcoTieneMesesComoAdminIgualALaDiferenciaEntreLaFechaActualYLaFechaDeAlta
	 * () { LocalDate fechaActual = LocalDate.now(); LocalDate fechaDeAlta =
	 * marco.getFechaAltaSistema(); long diferenciaDeMeses = ( fechaActual.getYear()
	 * - fechaDeAlta.getYear() ) * 12 + fechaActual.getMonthValue() -
	 * fechaDeAlta.getMonthValue();
	 * 
	 * assertEquals(diferenciaDeMeses, marco.cantidadDeMesesComoAdmin()); }
	 */

}
