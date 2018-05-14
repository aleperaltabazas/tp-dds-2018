package DDS.SGE;

import static org.junit.Assert.*;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import junit.framework.Assert;

public class TestCrearObjetos {

	Json builder = new Json();
	
	@Test
	public void CrearCliente() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String imgUrl =classLoader.getResource(".").getPath() + "..\\..\\src\\test\\java\\DDS\\SGE\\cliente.txt";
		
		Cliente unCliente = builder.crearCliente(imgUrl);
		
		Assert.assertEquals("gonzalo", unCliente.getNombre());
	}
	
	@Test
	public void CrearAdministrador() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String imgUrl =classLoader.getResource(".").getPath() + "..\\..\\src\\test\\java\\DDS\\SGE\\admin.txt";
		
		Administrador unAdministrador = builder.crearAdministrador(imgUrl);
		
		Assert.assertEquals("mati", unAdministrador.getNombre());
	}
	
	@Test
	public void CrearDispositivo() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String imgUrl =classLoader.getResource(".").getPath() + "..\\..\\src\\test\\java\\DDS\\SGE\\dispositivo.txt";
		
		Dispositivo unDispositivo = builder.crearDispositivo(imgUrl);

		Assert.assertEquals("dispositivox", unDispositivo.getNombre());
	}

}
