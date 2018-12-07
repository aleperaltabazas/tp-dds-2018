package MiscTest;

import java.io.File;

import DDS.SGE.Utils.JsonBuilder;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;

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

        DispositivoEstandar unDispositivoEstandar = jsonBuilder.crearDispositivoEstandar(file.getAbsolutePath());
        Dispositivo unDispositivo = new Dispositivo(unDispositivoEstandar);

        assertEquals(12.0, unDispositivo.obtenerConsumoKWPorHora(), 0.0);
    }

	/*
	@Test
	public void crearTransformador() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("transformador.txt").getFile());

		Transformador unTransformador = jsonBuilder.crearTransformador(file.getAbsolutePath());

		assertEquals(10.0, unTransformador.getZona().getRadio(), 0.0);
	}
	*/

    @Test
    public void crearZona() {
		/*
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("zona.txt").getFile());

		Zona unaZona = jsonBuilder.crearZona(file.getAbsolutePath());

		assertEquals(100.0, unaZona.getRadio(), 0.0);
		*/
    }
}
