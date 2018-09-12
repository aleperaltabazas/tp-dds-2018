package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class IntervaloActivo {
	LocalDateTime tiempoInicial;
	LocalDateTime tiempoFinal;
	
	public IntervaloActivo(LocalDateTime tiempoInicial, LocalDateTime tiempoFinal) {
		this.tiempoInicial = tiempoInicial;
		this.tiempoFinal = tiempoFinal;
	}
	
	public long getIntervaloEncendidoEnMinutos() {
		return ChronoUnit.MINUTES.between(tiempoInicial, tiempoFinal);
	}
	
	public boolean ocurreDespuesDe(LocalDateTime fecha) {
		return tiempoInicial.isAfter(fecha);
	}
	
	public boolean ocurreAntesDe(LocalDateTime fecha) {
		return !ocurreDespuesDe(fecha);
	}
}
