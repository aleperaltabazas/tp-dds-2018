package DDS.SGE.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Timer;

import DDS.SGE.Actuador.Bajar_Intensidad;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Luz implements Sensor {

	DispositivoInteligente dispositivo;
	Bajar_Intensidad actuador;
	
	public Luz(DispositivoInteligente dispositivo){
		this.dispositivo = dispositivo;
		this.actuador = new Bajar_Intensidad(20);
	}	

	public void Medir() {
		if (this.dispositivo.getIntensidad()> 50)
			this.actuador.accionarSobre(this.dispositivo);		
	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		long periodo = intervaloDeMinutos * 1000 * 60;
		Timer timer = new Timer();
		timer.schedule(new EjecutorDiferido(this),horaInicial.toEpochSecond(OffsetDateTime.now().getOffset()),periodo);
	}
}
