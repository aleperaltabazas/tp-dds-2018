package DDS.SGE;

import static org.junit.Assert.*;
import com.google.gson.*;

import java.util.Arrays;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import junit.framework.Assert;

public class testsGenericos {

	// Hago algunas instancias para los casos de prueba
	String jsonDisp = "{nombre:dispositivo x,consumoKWPorHora:12,encendido:true}";
	String jsonCliente = "{nombre:gonzalo,apellido:vaquero,tipoDni:dni,numeroDni:123,telefono:4444444,domicilio:calle falsa 123, fecha:15-15-2030,categoria:R1}";
	String jsonAdmin = "{nombre:mati,apellido:giorda,domicilio:calle falsa 321, fechaAlta:20-15-2030,identificador:2}";
	Dispositivo nintendoDS = new Dispositivo(50, false);
	Dispositivo televisor = new Dispositivo(70, false);
	Dispositivo plasma = new Dispositivo(40, false);
	Dispositivo computadora = new Dispositivo(140, true);
	Dispositivo estufaElectrica = new Dispositivo(200, false);
	// No estoy muy seguro si conviene usar listas de esta manera
	List<Dispositivo> dispositivosDeAlejandro = Arrays.asList(televisor, nintendoDS);
	List<Dispositivo> dispositivosDeLucila = Arrays.asList(televisor, computadora);
	Cliente alejandro = new Cliente("Alejandro","Peralta",TipoDni.dni,"123456789","1144448888","Av siempre viva 123", Calendar.getInstance() , Categoria.R1, dispositivosDeAlejandro);
	Cliente lucila = new Cliente("Lucila","Salmeron",TipoDni.dni,"123456789","1144448888","Av siempre viva 123", Calendar.getInstance(), Categoria.R1, dispositivosDeLucila);
	
	@Test
	public void castearJson() {
		Dispositivo disp = new Dispositivo(10,true);
		disp.cargarDesdeJson(jsonDisp);
		
		assertEquals("dispositivo x", disp.nombre);
	}
	
	@Test
	public void crearCliente() {
		Cliente unCliente = new Cliente(jsonCliente);
		
		assertEquals("gonzalo", unCliente.nombre);
	}
	
	// Esto deberia crear un cliente directamente desde el json - hay que ver como quedarian los constructores y demas
	// Mientras no ande dejo la carga de json con json.org 
	@Test
	public void crearClienteConGson() {
		Gson gson = new GsonBuilder().create();	
		String gsonDisp = "{'nombre':'dispositivo x','consumoKWPorHora':'12','encendido':'true'}";
		Dispositivo disp= gson.fromJson(gsonDisp, Dispositivo.class);
		
		assertEquals("dispositivo x", disp.nombre);
	}
	
	@Test
	public void crearDispositivoConGson() {
		Gson gson = new GsonBuilder().create();	
		String gsonCliente = "{'nombre':'gonzalo','apellido':'vaquero','tipoDni':'DNI','numeroDocumento':'123','telefono':'4444444','domicilio':'calle falsa 123', 'fecha':'15-15-2030','categoria':'R1'}";		
		Cliente cliente= gson.fromJson(gsonCliente, Cliente.class);
		
		assertEquals("gonzalo", cliente.nombre);
	}
	
	@Test
	public void testAlejandroNoTieneNingunDispositivoEncendido() {
		assertFalse(alejandro.algunDispositivoEncendido());
	}

	@Test
	public void testLucilaTieneAlgunDispositivoEncendido() {
		assertTrue(lucila.algunDispositivoEncendido());
	}
	
	@Test
	public void testElConsumoPorHoraDeAlejandroEs0Kwh() {
		assertEquals(0, 0, alejandro.consumoTotalPorHora());
	}

	@Test
	public void testLucilaTiene1DispositivoEncendido() {
		assertEquals(1, lucila.dispositivosEncendidos());
	}

	@Test
	public void testAlEncenderseLosDispositivosDeAlejandroSuConsumoPorHoraEsLaSumaDelConsumoPorHoraDeAmbosDispositivos() {
		plasma.encender();
		nintendoDS.encender();
		assertEquals(plasma.getConsumoKWPorHora() + nintendoDS.getConsumoKWPorHora(), 0,
				alejandro.consumoTotalPorHora());
	}

	@Test
	public void testLaCategoriaDeLucilaEsR1() {
		lucila.recategorizar();
		assertEquals(Categoria.R1, lucila.getCategoria());
	}

	@Test
	public void testAlEncenderLaComputadoraLaRecategorizacionDeLucilaEsR2() {
		televisor.encender();
		lucila.recategorizar();
		assertEquals(Categoria.R2, lucila.getCategoria());
	}

	@Test
	public void testLaFacturacionEstimadaVariableDeLucilaEsCorrespondienteALaCategoria1() {
		assertEquals(lucila.consumoTotalPorHora() * Categoria.R1.getNormalVariable(), 0,
				lucila.getCategoria().estimarFacturacionCargoVariable(lucila));
	}
	
	@Test
	public void testLaFacturacionEstimadaVariableDeLucilaTrasEncenderLaImpresoraEsCorrespondienteALaCategoria2() {
		estufaElectrica.encender();
		lucila.recategorizar();
		assertEquals(lucila.consumoTotalPorHora() * Categoria.R3.getNormalVariable(), 0,
				lucila.getCategoria().estimarFacturacionCargoVariable(lucila));
	}
}

