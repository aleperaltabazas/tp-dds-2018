package DDS.SGE;

import java.io.File;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;
import static org.junit.Assert.*;

public class TestCrearObjetos {

	JsonBuilder jsonBuilder = new JsonBuilder();

	@Test
	public void crearCliente() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cliente.txt").getFile());

		Cliente unCliente = jsonBuilder.crearCliente(file.getAbsolutePath());

		assertEquals("gonzalo", unCliente.getNombre());
	}

	@Test
	public void crearAdministrador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("admin.txt").getFile());

		Administrador unAdministrador = jsonBuilder.crearAdministrador(file.getAbsolutePath());

		assertEquals("mati", unAdministrador.getNombre());
	}

	@Test
	public void crearDispositivo() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("dispositivo.txt").getFile());

		Dispositivo unDispositivo = jsonBuilder.crearDispositivo(file.getAbsolutePath());

		// Habria que ver como hacemos para crearle el tipo desde el archivo, porque el
		// Dispositivo le pregunta
		// a su tipo cuanto consumoKWPorHora tiene
		assertEquals(20.0, unDispositivo.obtenerConsumoKWPorHora(), 0.0);
	}

	// El test de transformador y de zona fallan porque no está levantando
	// correctamente los txts

	@Test
	public void crearTransformador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("transformador.txt").getFile());

		Transformador unTransformador = jsonBuilder.crearTransformador(file.getAbsolutePath());

		assertEquals(10.0, unTransformador.getZona().getRadio(), 0.0);
	}

	@Test
	public void crearZona() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("zona.txt").getFile());

		Zona unaZona = jsonBuilder.crearZona(file.getAbsolutePath());

		assertEquals(10.0, unaZona.getRadio(), 0.0);
	}
}
