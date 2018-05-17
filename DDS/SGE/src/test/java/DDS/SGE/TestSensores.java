package DDS.SGE;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;
import DDS.SGE.Sensor.Temperatura;

public class TestSensores {

	Dispositivo unDispositivo = new Dispositivo(10,new DispositivoInteligente(new Apagado()));
	DispositivoInteligente inteligente = new DispositivoInteligente(new Encendido());
	
	@Test
	public void SiHaceCalorSeCambiaAFrio() {
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Mockito.when(mockInteligente.getTemperaturaAmbiente()).thenReturn(30.0);
		Temperatura sensorTemperatura = new Temperatura();
		sensorTemperatura.controlar(mockInteligente);
		assertTrue(mockInteligente.getEstado().getModo() == ModoFrio_Calor.FRIO);
	}
}
