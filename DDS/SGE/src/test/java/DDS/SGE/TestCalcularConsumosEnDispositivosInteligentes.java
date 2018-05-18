package DDS.SGE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.RepositorioDeTiempoEncendido;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;

public class TestCalcularConsumosEnDispositivosInteligentes {
	
	LocalDateTime fechaDeReferencia = LocalDateTime.now();
	IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
	IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5), fechaDeReferencia.minusHours(3));
	List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
	RepositorioDeTiempoEncendidoTest repositorioDePrueba = new RepositorioDeTiempoEncendidoTest(intervalosDeActividad);
	DispositivoInteligente dispositivoInteligenteEncendido = new DispositivoInteligente(new Encendido());
	Dispositivo dispositivoEncendido;
	
	@Before
	public void initialize() {
		dispositivoInteligenteEncendido.setRepositorio(repositorioDePrueba);
		dispositivoEncendido = new Dispositivo(2, dispositivoInteligenteEncendido);		
	}
	
	@Test
	public void testElTiempoDelDispositivoEncendidoEnLasUltimas6HorasEsDe180Minutos() {
		assertEquals(3 * 60, dispositivoInteligenteEncendido.tiempoTotalEncendidoHaceNHoras(10), 0);
	}
	
	@Test
	public void testElTiempoDelDispositivoEncendidoEnLasUltimas2HorasEsDe60Minutos() {
		assertEquals(1 * 60, dispositivoInteligenteEncendido.tiempoTotalEncendidoHaceNHoras(2), 0);
	}
	
	@Test
	public void testElTiempoDelDispositivoEnUnPeriodoQueIncluyeSoloAlIntervaloDe2HorasEsDe120Minutos() {
		LocalDateTime finPeriodo = fechaDeReferencia.minusHours(2);
		LocalDateTime principioPeriodo = finPeriodo.minusHours(5);
		assertEquals(2 * 60, dispositivoInteligenteEncendido.tiempoTotalEncendidoEnUnPeriodo(principioPeriodo, finPeriodo), 0);
	}
	
	@Test
	public void testElConsumoDiarioEstimadoDelDispositivoEncendidoEsDe360Kwh() {
		assertEquals(2 * (1 + 2) * 60, dispositivoEncendido.consumoDiarioEstimado(), 0);
	}
	

}
