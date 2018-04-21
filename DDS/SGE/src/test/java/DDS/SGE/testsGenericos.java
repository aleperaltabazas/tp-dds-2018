package DDS.SGE;

import static org.junit.Assert.*;

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
	String jsonCliente = "{nombre:gonzalo,apellido:vaquero,tipoDni:DNI,numeroDocumento:123,telefono:4444444,domicilio:calle falsa 123, fecha:15-15-2030,categoria:R1}";
	String jsonAdmin;
	Dispositivo nintendoDS = new Dispositivo(50, false);
	Dispositivo televisor = new Dispositivo(70, false);
	Dispositivo computadora = new Dispositivo(100, true);
	// No estoy muy seguro si conviene usar listas de esta manera
	List<Dispositivo> dispositivosDeAlejandro = Arrays.asList(televisor, nintendoDS);
	List<Dispositivo> dispositivosDeLucila = Arrays.asList(televisor, computadora);
	Cliente alejandro = new Cliente("Alejandro","Peralta",TipoDni.dni,"123456789","1144448888","Av siempre viva 123", Calendar.getInstance() , Categoria.R1, dispositivosDeAlejandro);
	Cliente lucila = new Cliente("Lucila","Salmeron",TipoDni.dni,"123456789","1144448888","Av siempre viva 123", Calendar.getInstance(), Categoria.R1, dispositivosDeLucila);

	// Hago 3 tests de cargas genericas de JSON para ya tener la estructura de
	// usuario y dispositivos
	@Test
	public void CargarJsonCliente() {
		JSONObject obj = new JSONObject(
				"{nombre:gonzalo,apellido:vaquero,tipoDni:DNI,numeroDocumento:123,telefono:4444444,domicilio:calle falsa 123, fecha:15-15-2030,categoria:R1}");
		// Habria que ver el tema de la fecha
		String nombre = obj.getString("nombre");

		assertEquals("gonzalo", nombre);
	}

	@Test
	public void CargarJsonAdministrador() {
		JSONObject obj = new JSONObject(
				"{nombre:mati,apellido:giorda,domicilio:calle falsa 321, fechaAlta:20-15-2030,identificador:2}");
		// Habria que ver el tema de la fecha
		String nombre = obj.getString("nombre");

		assertEquals("mati", nombre);
	}

	@Test
	public void CargarJsonDispositivo() {
		JSONObject obj = new JSONObject("{nombre:dispositivo x,consumoKWPorHora:12,encendido:true}");
		String nombre = obj.getString("nombre");
		Boolean encendido = obj.getBoolean("encendido");

		assertEquals("dispositivo x", nombre);
		assertEquals(true, encendido);
	}

	@Test
	public void GenerarJsonGenerico() {
		JSONObject obj = new JSONObject();

		obj.put("name", "gonza");
		obj.put("num", new Integer(100));
		obj.put("double", new Double(1000.21));
		obj.put("boolean", new Boolean(true));

		assertEquals("gonza", obj.getString("name"));
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
	public void castearJson() {
		Dispositivo disp = new Dispositivo(10,true);
		disp.cargarDesdeJson(jsonDisp);
		
		assertEquals("dispositivo x", disp.nombre);
	}
}
