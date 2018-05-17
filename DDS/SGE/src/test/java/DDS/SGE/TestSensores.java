package DDS.SGE;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Sensor.Temperatura;

public class TestSensores {

	Dispositivo unDispositivo = new Dispositivo(10,new DispositivoInteligente(new Apagado()));
	Temperatura sensorTemperatura = new Temperatura(unDispositivo);
	
	@Test
	public void test() {
		Temperatura mockSensor = Mockito.spy(sensorTemperatura);
		Mockito.when(mockSensor.Medir()).thenReturn(20.4);
		
		assertEquals(20.4, mockSensor.Medir(),0);
	}
}
