package DDS.SGE;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import junit.framework.Assert;

public class testsGenericos {
	
	@Test
	public void CargarJsonUsuario() {
		JSONObject obj = new JSONObject("{nombre:gonzalo,apellido:vaquero,tipoDni:DNI,numeroDocumento:123,telefono:4444444,domicilio:calle falsa 123, fecha:15-15-2030,categoria:R1}") ;
		//Habria que ver el tema de la fecha
		String nombre = obj.getString("nombre");
		
		assertEquals("gonzalo", nombre);
	}
	
	@Test
	public void CargarJsonDispositivo() {
		JSONObject obj = new JSONObject("{nombre:dispositivo x,kwPorHora:12,encendido:true}") ;
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
}
