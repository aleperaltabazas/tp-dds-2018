package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IntervaloActivo {
	@Id
	private Long id;
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

	public LocalDateTime getTiempoInicial() {
		return this.tiempoInicial;
	}
}
