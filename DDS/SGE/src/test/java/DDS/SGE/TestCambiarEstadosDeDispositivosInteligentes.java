package DDS.SGE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.AhorroDeEnergia;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;

import org.junit.Before;

public class TestCambiarEstadosDeDispositivosInteligentes {
	
	Apagado estadoApagado = new Apagado();
	Encendido estadoEncendido = new Encendido();
	AhorroDeEnergia estadoAhorroDeEnergia = new AhorroDeEnergia();
	
	Computadora unFabricante = new Computadora(true);
	
	DispositivoInteligente dispositivoInteligenteApagado = new DispositivoInteligente(estadoApagado, unFabricante);
	DispositivoInteligente dispositivoInteligenteEncendido = new DispositivoInteligente(estadoEncendido, unFabricante);
	DispositivoInteligente dispositivoInteligenteEnAhorroDeEnergia = new DispositivoInteligente(estadoAhorroDeEnergia, unFabricante);
	
	Dispositivo unDispositivoApagado = new Dispositivo(1, dispositivoInteligenteApagado);
	Dispositivo unDispositivoEncendido = new Dispositivo(1, dispositivoInteligenteEncendido);
	Dispositivo unDispositivoEnAhorroDeEnergia = new Dispositivo(1, dispositivoInteligenteEnAhorroDeEnergia);
	
	@Test
	public void testSeEnciendeUnDispositivoEnModoAhorroDeEnergiaYQuedaEncendido() {
		unDispositivoEnAhorroDeEnergia.encender();
		assertTrue(dispositivoInteligenteEnAhorroDeEnergia.getEstado() instanceof Encendido);
	}
	
	@Test
	public void testSeApagaUnDispositivoEnModoAhorroDeEnergiaYQuedaApagado() {
		unDispositivoEnAhorroDeEnergia.apagar();
		assertTrue(dispositivoInteligenteEnAhorroDeEnergia.getEstado() instanceof Apagado);
	}
	
	@Test
	public void testSeEnciendeUnDispositivoEncendidoYNoPasaNada() {
		unDispositivoEncendido.encender();
		assertTrue(dispositivoInteligenteEncendido.getEstado() instanceof Encendido);
	}
	
	@Test
	public void testSeApagaUnDispositivoApagadoYNoPasaNada() {
		unDispositivoApagado.apagar();
		assertTrue(dispositivoInteligenteApagado.getEstado() instanceof Apagado);
	}
	
	@Test
	public void testSeEnciendeUnDispositivoApagadoYQuedaEncendido() {
		unDispositivoApagado.encender();
		assertTrue(dispositivoInteligenteApagado.getEstado() instanceof Encendido);
	}
	
	@Test
	public void testSeApagaUnDispositivoEncendidoYQuedaApagado() {
		unDispositivoEncendido.apagar();
		assertTrue(dispositivoInteligenteEncendido.getEstado() instanceof Apagado);
	}
	
}
