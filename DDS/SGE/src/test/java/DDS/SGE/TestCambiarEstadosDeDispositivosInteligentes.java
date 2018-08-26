package DDS.SGE;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.AhorroDeEnergia;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;

public class TestCambiarEstadosDeDispositivosInteligentes {
	
	Apagado estadoApagado = new Apagado();
	Encendido estadoEncendido = new Encendido();
	AhorroDeEnergia estadoAhorroDeEnergia = new AhorroDeEnergia();
	
	FabricanteTest unFabricante = new FabricanteTest(1);
	
	DispositivoInteligente dispositivoInteligenteApagado = new DispositivoInteligente(estadoApagado, unFabricante);
	DispositivoInteligente dispositivoInteligenteEncendido = new DispositivoInteligente(estadoEncendido, unFabricante);
	DispositivoInteligente dispositivoInteligenteEnAhorroDeEnergia = new DispositivoInteligente(estadoAhorroDeEnergia, unFabricante);
	
	Dispositivo unDispositivoApagado = new Dispositivo(dispositivoInteligenteApagado);
	Dispositivo unDispositivoEncendido = new Dispositivo(dispositivoInteligenteEncendido);
	Dispositivo unDispositivoEnAhorroDeEnergia = new Dispositivo(dispositivoInteligenteEnAhorroDeEnergia);
	
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
