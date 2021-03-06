package DDS.SGE.OptimizadorTest;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Actuador.CambiarTemperaturaAireAcondicionado;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Regla.Regla;
import DDS.SGE.Sensor.Temperatura;
import DDS.SGE.Fabricante.AireAcondicionado;

public class TestSensores {

	AireAcondicionado unAireAcondicionado = new AireAcondicionado(3500);
	
	DispositivoInteligente unDispositivoInteligente = new DispositivoInteligente(new Encendido(), unAireAcondicionado);
	
	Temperatura sensorTemperatura = new Temperatura(22, unDispositivoInteligente);
	
	CambiarTemperaturaAireAcondicionado actuadorTemperatura = new CambiarTemperaturaAireAcondicionado(unDispositivoInteligente);
	
	Regla elAireSiempreEn24 = new Regla(Arrays.asList(sensorTemperatura), actuadorTemperatura);
	
	@Before
	public void initialize() {
		unDispositivoInteligente.setFabricante(unAireAcondicionado);
		unAireAcondicionado.ponerElAireEn(16);
	}
	
	@Test
	public void SiElAireAcondicionadoEstaAMenosDe24YLaTemperaturaAmbienteEsBajaElAireSePoneEn24() {
		
		elAireSiempreEn24.actuar();
		assertEquals(24, unAireAcondicionado.getTemperaturaDelDispositivo(), 0);
	}
	
	@Test
	public void SiElAireAcondicionadoEstaAMenosDe24YEnLaTemperaturaAmbienteEsAltaElAireNoSePoneEn24() {
		sensorTemperatura.setTemperaturaAmbiente(40);
		elAireSiempreEn24.actuar();
		assertEquals(16, unAireAcondicionado.getTemperaturaDelDispositivo(), 0);
	}

}
