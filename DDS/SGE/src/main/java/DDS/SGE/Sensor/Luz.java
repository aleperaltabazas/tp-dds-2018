package DDS.SGE.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Timer;

import DDS.SGE.Dispositivo;

public class Luz implements Sensor {

	Dispositivo dispositivo;
	
	Luz(Dispositivo dispositivo){
		this.dispositivo = dispositivo;
	}	
	
	@Override
	public double Medir() {
		return this.dispositivo.getConsumoKWPorHora();
	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		long periodo = intervaloDeMinutos * 1000 * 60;
		Timer timer = new Timer();
		timer.schedule(new EjecutorDiferido(this),horaInicial.toEpochSecond(OffsetDateTime.now().getOffset()),periodo);
	}

}
