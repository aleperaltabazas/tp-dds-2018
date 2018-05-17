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
	Bajar_Intensidad actuador_Bajar_Int;
	
	public void setActuador_Bajar_Int(double intensidadABajar){
		this.actuador_Bajar_Int = new Bajar_Intensidad(intensidadABajar);
	}	

	public void controlar(DispositivoInteligente unDispositivo) {
		if (unDispositivo.getIntensidad()> 50)
			this.actuador_Bajar_Int.accionarSobre(unDispositivo);		
	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		long periodo = intervaloDeMinutos * 1000 * 60;
		Timer timer = new Timer();
		timer.schedule(new EjecutorDiferido(this),horaInicial.toEpochSecond(OffsetDateTime.now().getOffset()),periodo);
	}

	@Override
	public double medir(DispositivoInteligente unDispositivo) {
		return unDispositivo.getEstado().getIntensidad();
		
	}

	
}
