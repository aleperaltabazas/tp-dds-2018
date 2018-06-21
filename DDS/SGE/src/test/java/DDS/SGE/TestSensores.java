package DDS.SGE;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import DDS.SGE.Actuador.CambiarTemperaturaAireAcondicionado;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;
import DDS.SGE.Regla.Regla;
import DDS.SGE.Sensor.Luz;
import DDS.SGE.Sensor.Temperatura;
import Fabricante.AireAcondicionado;

public class TestSensores {

	AireAcondicionado unAireAcondicionado = new AireAcondicionado(3500);
	
	DispositivoInteligente unDispositivoInteligente = new DispositivoInteligente(new Encendido());
	
	Temperatura sensorTemperatura = new Temperatura(unDispositivoInteligente, 22);
	
	CambiarTemperaturaAireAcondicionado actuadorTemperatura = new CambiarTemperaturaAireAcondicionado();
	
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
	
	/*
	@Test
	public void SiHaceCalorSeCambiaAFrio() {
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Temperatura sensorTemperatura = new Temperatura(29);
		sensorTemperatura.controlar(mockInteligente);
		assertEquals(mockInteligente.getEstado().getModo(), ModoFrio_Calor.FRIO);
	}
	
	@Test
	public void SiHaceFrioSeCambiaACalor() {
		DispositivoInteligente mockInteligente = Mockito.spy(inteligente);
		Temperatura sensorTemperatura = new Temperatura(10);
		sensorTemperatura.controlar(mockInteligente);
		assertEquals(mockInteligente.getEstado().getModo(), ModoFrio_Calor.CALOR);
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
	*/
}
