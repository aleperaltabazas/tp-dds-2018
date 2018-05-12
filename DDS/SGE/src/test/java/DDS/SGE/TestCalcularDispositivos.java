package DDS.SGE;

import static org.junit.Assert.*;
import com.google.gson.*;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import junit.framework.Assert;

public class TestCalcularDispositivos {
	
	Dispositivo dispositivoEncendido = new Dispositivo(1, true);
	Dispositivo dispositivoApagado = new Dispositivo(1, false);
	Cliente clienteSinDispositivos;
	Cliente clienteConVariosDispostivos;
	Cliente clienteConTodoApagado;
	
	@Before
	public void initialize() {
		 /*clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
					"Av siempre viva 742", LocalDate.now(), sinDispositivos);*/
		 clienteConVariosDispostivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
					"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoEncendido, dispositivoApagado, dispositivoEncendido, dispositivoEncendido));
		 clienteConTodoApagado = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
					"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoApagado, dispositivoApagado));
	}
	
	/* No funciona
	@Test
	public void testUnClienteSinDispositivosNoTieneDispositivosEncendidos() {
		assertTrue(clienteSinDispositivos.algunDispositivoEncendido());
	}*/
	
	@Test
	public void testElClienteDerrochadorTieneAlgunDispositivoEncendido() {
		assertTrue(clienteConVariosDispostivos.algunDispositivoEncendido());
	}
	
	@Test
	public void testUnClienteConTodosSusDispositivosApagadosNoTieneAlgunDispositivoEncendido() {
		assertFalse(clienteConTodoApagado.algunDispositivoEncendido());
	}
	
	@Test
	public void testElClienteDerrochadorTiene3DispositivoEncendido() {
		assertEquals(3, clienteConVariosDispostivos.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void testElClienteDerrochadorTiene4Dispositivos() {
		assertEquals(4, clienteConVariosDispostivos.cantidadDispositivos());
	}
	
	@Test
	public void testElClienteDerrochadorTiene1DispositivoApagado() {
		assertEquals(1, clienteConVariosDispostivos.cantidadDispositivosApagados());
	}
	
	@Test
	public void testUnClienteConTodosSusDispositivosApagadosLaCantidadDeDispostivosCoincideConLaDeDispostivosApagados() {
		assertEquals(clienteConTodoApagado.cantidadDispositivosApagados(), clienteConTodoApagado.cantidadDispositivos());
	}
	
	@Test
	public void testElClienteConTodoApagadoPrendeYApagaSusDispositivosYAdquiereOtroPlasmaApagadoYNoTieneDispositivosEncendidos() {
		dispositivoApagado.encender();
		dispositivoApagado.apagar();		

		Dispositivo nuevoDispositivoApagado = new Dispositivo(10, false);
		
		List<Dispositivo> nuevosDispositivos = Arrays.asList(dispositivoApagado, nuevoDispositivoApagado);
		clienteConTodoApagado.setDispositivos(nuevosDispositivos);

		assertFalse(clienteConTodoApagado.algunDispositivoEncendido());

	}
}
