package DDS.SGE;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.*;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;
import DDS.SGE.Sensor.Luz;
import DDS.SGE.Sensor.Temperatura;

public class TestSensores {

	Dispositivo unDispositivo = new Dispositivo(10,new DispositivoInteligente(new Apagado()));
	DispositivoInteligente inteligente = new DispositivoInteligente(new Encendido());
	
	@Test
	public void SiHaceCalorSeCambiaAFrio() {
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Temperatura sensorTemperatura = new Temperatura(29);
		sensorTemperatura.controlar(mockInteligente);
		assertTrue(mockInteligente.getEstado().getModo() == ModoFrio_Calor.FRIO);
	}
	
	@Test
	public void SiHaceFrioSeCambiaACalor() {
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Temperatura sensorTemperatura = new Temperatura(10);
		sensorTemperatura.controlar(mockInteligente);
		assertTrue(mockInteligente.getEstado().getModo() == ModoFrio_Calor.CALOR);
	}
	
	@Test
	public void SiHayPocaLuzBajaIntensidad() { // Pensando en una pantalla
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Luz sensorLuz = new Luz(10);
		sensorLuz.setActuador_Bajar_Int(15);
		mockInteligente.setIntensidad(20);
		sensorLuz.controlar(mockInteligente);
		assertTrue(mockInteligente.getEstado().getIntensidad() <= sensorLuz.getIntensidadLuminicaDelAmbiente());
	}
	
	@Test
	public void SiHayMuchaLuzSubeIntensidad() { // Pensando en una pantalla
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Luz sensorLuz = new Luz(50);
		mockInteligente.setIntensidad(20);
		sensorLuz.controlar(mockInteligente);
		assertTrue(mockInteligente.getEstado().getIntensidad() >= sensorLuz.getIntensidadLuminicaDelAmbiente());
	}
}
