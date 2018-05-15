package DDS.SGE;

import static org.junit.Assert.assertEquals;

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

import org.junit.Before;

public class TestCambiarEstadosDeDispositivosInteligentes {
	
	Apagado estadoApagado = new Apagado();
	Encendido estadoEncendido = new Encendido();
	AhorroDeEnergia estadoAhorroDeEnergia = new AhorroDeEnergia();
	
	DispositivoInteligente dispositivoInteligenteApagado = new DispositivoInteligente(estadoApagado);
	DispositivoInteligente dispositivoInteligenteEncendido = new DispositivoInteligente(estadoEncendido);
	DispositivoInteligente dispositivoInteligenteEnAhorroDeEnergia = new DispositivoInteligente(estadoAhorroDeEnergia);
	
	Dispositivo unDispositivoApagado = new Dispositivo(1, dispositivoInteligenteApagado);
	Dispositivo unDispositivoEncendido = new Dispositivo(1, dispositivoInteligenteEncendido);
	Dispositivo unDispositivoEnAhorroDeEnergia = new Dispositivo(1, dispositivoInteligenteEnAhorroDeEnergia);
	
	@Test
	public void testSeEnciendeUnDispositivoEnModoAhorroDeEnergiaYQuedaEncendido() {
		unDispositivoEnAhorroDeEnergia.recibirActuador(new ActuadorEncenderse);
		assertEquals(unDispositivoEnAhorroDeEnergia.estaEncendido(), new Encendido());
	}
	
}
