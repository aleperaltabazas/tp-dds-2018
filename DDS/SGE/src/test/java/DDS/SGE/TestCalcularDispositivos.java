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
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;
import Geoposicionamiento.Zona;
import junit.framework.Assert;

public class TestCalcularDispositivos {
	
	Computadora unFabricante = new Computadora(true);
	Zona unaZona = new Zona();
	
	Dispositivo dispositivoEncendido = new Dispositivo(1, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoApagado = new Dispositivo(1, new DispositivoInteligente(new Apagado()), unFabricante);
	Cliente clienteSinDispositivos;
	Cliente clienteConVariosDispostivos;
	Cliente clienteConTodoApagado;
	
	@Before
	public void initialize() {
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(), unaZona);
		clienteConVariosDispostivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoEncendido, dispositivoApagado, dispositivoEncendido, dispositivoEncendido), unaZona);
		clienteConTodoApagado = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoApagado, dispositivoApagado), unaZona);
	}
	
	@Test
	public void testUnClienteSinDispositivosNoTieneDispositivosEncendidos() {
		assertFalse(clienteSinDispositivos.algunDispositivoEncendido());
	}
	
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

		Dispositivo nuevoDispositivoApagado = new Dispositivo(10, new DispositivoInteligente(new Apagado()), unFabricante);
		
		List<Dispositivo> nuevosDispositivos = Arrays.asList(dispositivoApagado, nuevoDispositivoApagado);
		clienteConTodoApagado.setDispositivos(nuevosDispositivos);

		assertFalse(clienteConTodoApagado.algunDispositivoEncendido());

	}
}
