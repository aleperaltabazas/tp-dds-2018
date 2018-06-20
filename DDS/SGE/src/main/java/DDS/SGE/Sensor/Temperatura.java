package DDS.SGE.Sensor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Timer;

import DDS.SGE.Actuador.CambiarACalor;
import DDS.SGE.Actuador.CambiarAFrio;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Temperatura implements Sensor {
	DispositivoInteligente dispositivo;
	double temperaturaAmbiente = 22;

	public Temperatura(DispositivoInteligente dispositivo, double temperaturaAmbiente) {
		this.dispositivo = dispositivo;
		this.temperaturaAmbiente = temperaturaAmbiente;
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

	public double getTemperaturaAmbiente() {
		return temperaturaAmbiente;
	}

	public void setTemperaturaAmbiente(double temperaturaAmbiente) {
		this.temperaturaAmbiente = temperaturaAmbiente;
	}
	
	public double medir() {
		//De alguna manera sensa el ambiente y hace un setTemperatura() con la medición
		return temperaturaAmbiente;
	}
	
	public boolean hayQueActuar() {
		return dispositivo.hayQueActuar(this.temperaturaAmbiente);
	}

}
