package DDS.SGE;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;
import junit.framework.Assert;

public class TestCrearObjetos {

	JsonBuilder jsonBuilder = new JsonBuilder();
	
	@Test
	public void crearCliente() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cliente.txt").getFile());
		
		Cliente unCliente = jsonBuilder.crearCliente(file.getAbsolutePath());
		
		Assert.assertEquals("gonzalo", unCliente.getNombre());
	}
	
	@Test
	public void crearAdministrador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("admin.txt").getFile());
		
		Administrador unAdministrador = jsonBuilder.crearAdministrador(file.getAbsolutePath());
		
		Assert.assertEquals("mati", unAdministrador.getNombre());
	}
	
	@Test
	public void crearDispositivo() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("dispositivo.txt").getFile());
		
		Dispositivo unDispositivo = jsonBuilder.crearDispositivo(file.getAbsolutePath());

		Assert.assertEquals(12.0, unDispositivo.getConsumoKWPorHora(),0.0);
	}
	
	@Test
	public void crearTransformador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("transformador.txt").getFile());
		
		Transformador unTransformador = jsonBuilder.crearTransformador(file.getAbsolutePath());

		Assert.assertEquals(10.0, unTransformador.suministra(),0.0);
	}
	
	@Test
	public void crearZona() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("zona.txt").getFile());
		
		Zona unaZona = jsonBuilder.crearZona(file.getAbsolutePath());

		Assert.assertEquals(10.0, unaZona.consumoTotal(),0.0);
	}
}
