package DDS.SGE.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Timer;

import DDS.SGE.Actuador.Bajar_Intensidad;
import DDS.SGE.Actuador.Subir_Intensidad;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Luz implements Sensor {

	Bajar_Intensidad actuador_Bajar_Int;
	Subir_Intensidad actuador_Subir_Int = new Subir_Intensidad(15);
	double intensidadLuminicaAmbiente;
	
	
	public Luz(double luzAmbiente) {
		this.intensidadLuminicaAmbiente = luzAmbiente;
	}
	
	public double getIntensidadLuminicaDelAmbiente() {
		return this.intensidadLuminicaAmbiente;
	}
	
	public void setActuador_Bajar_Int(double intensidadABajar){
		this.actuador_Bajar_Int = new Bajar_Intensidad(intensidadABajar);
	}	

	public void controlar(DispositivoInteligente unDispositivo) {
		if (unDispositivo.getIntensidad() > this.intensidadLuminicaAmbiente) {
			do {
				this.actuador_Bajar_Int.accionarSobre(unDispositivo);		
			} while (unDispositivo.getIntensidad() > this.intensidadLuminicaAmbiente || unDispositivo.getIntensidad() < 0);
		} 
		else { 
			do {
				this.actuador_Subir_Int.accionarSobre(unDispositivo);		
			} while (unDispositivo.getIntensidad() < this.intensidadLuminicaAmbiente);
		}
	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos, DispositivoInteligente unDispositivo) {
		long periodo = intervaloDeMinutos * 1000 * 60;
		Timer timer = new Timer();
		timer.schedule(new EjecutorDiferido(this, unDispositivo),horaInicial.toEpochSecond(OffsetDateTime.now().getOffset()),periodo);
	}

	@Override
	public double medir() {
		return this.intensidadLuminicaAmbiente;		
	}
	
	@Override
	public double medir(DispositivoInteligente unDispositivo) {
		return unDispositivo.getIntensidad();		
	}	
}
