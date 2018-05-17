package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class IntervaloActivo {
	LocalDateTime tiempoInicial;
	LocalDateTime tiempoFinal;
	long intervaloEncendidoEnMinutos;
	
	public IntervaloActivo(LocalDateTime tiempoInicial, LocalDateTime tiempoFinal) {
		this.tiempoInicial = tiempoInicial;
		this.tiempoFinal = tiempoFinal;
		intervaloEncendidoEnMinutos = ChronoUnit.MINUTES.between(tiempoInicial, tiempoFinal);		
	}
	
	public long getIntervaloEncendidoEnMinutos() {
		return intervaloEncendidoEnMinutos;
	}
	
	public boolean ocurreDespuesDe(LocalDateTime fecha) {
		return tiempoInicial.isAfter(fecha);
	}
	
	public boolean ocurreAntesDe(LocalDateTime fecha) {
		return !ocurreDespuesDe(fecha);
	}
}
