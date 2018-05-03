package DDS.SGE;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class TestCrearObjetos {

	Json builder = new Json();
	
	@Test
	public void CrearCliente() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Cliente unCliente = builder.crearCliente("..\\..\\src\\test\\java\\DDS\\SGE\\cliente.txt");
		
		Assert.assertEquals("gonzalo", unCliente.getNombre());
	}
	
	@Test
	public void CrearAdministrador() {
		Administrador unAdministrador = builder.crearAdministrador("C:\\Users\\Usuario\\Documents\\UTN\\Diseño de sistemas\\2018-vn-group-11\\DDS\\SGE\\src\\test\\java\\DDS\\SGE\\administrador.txt");
		
		Assert.assertEquals("mati", unAdministrador.getNombre());
	}
	
	@Test
	public void CrearDispositivo() {
		//Dispositivo unDispositivo = builder.crearDispositivo("C:\\Users\\Usuario\\Documents\\UTN\\Diseño de sistemas\\2018-vn-group-11\\DDS\\SGE\\src\\test\\java\\DDS\\SGE\\dispositivo.txt");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String imgUrl =classLoader.getResource(".").getPath() + "..\\..\\src\\test\\java\\DDS\\SGE\\cliente.txt";
		Assert.assertEquals("algo", imgUrl);
		//Assert.assertEquals("dispositivo x", unDispositivo.getNombre());
	}

}
