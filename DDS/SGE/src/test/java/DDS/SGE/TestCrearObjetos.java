package DDS.SGE;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import junit.framework.Assert;

public class TestCrearObjetos {

	Json jsonBuilder = new Json();
	
	@Test
	public void CrearCliente() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cliente.txt").getFile());
		
		Cliente unCliente = jsonBuilder.crearCliente(file.getAbsolutePath());
		
		Assert.assertEquals("gonzalo", unCliente.getNombre());
	}
	
	@Test
	public void CrearAdministrador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("admin.txt").getFile());
		
		Administrador unAdministrador = jsonBuilder.crearAdministrador(file.getAbsolutePath());
		
		Assert.assertEquals("mati", unAdministrador.getNombre());
	}
	
	@Test
	public void CrearDispositivo() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("dispositivo.txt").getFile());
		
		Dispositivo unDispositivo = jsonBuilder.crearDispositivo(file.getAbsolutePath());

		Assert.assertEquals("dispositivox", unDispositivo.getNombre());
	}

}
