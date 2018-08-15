package DDS.SGE.Sensor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Timer;

import DDS.SGE.Actuador.CambiarACalor;
import DDS.SGE.Actuador.CambiarAFrio;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Temperatura implements Sensor {

	double temperaturaAmbiente = 22;
	double temperaturaActual;

	public Temperatura(double temperaturaAmbiente) {
		this.temperaturaAmbiente = temperaturaAmbiente;
	}

	public double getTemperaturaAmbiente() {
		return temperaturaAmbiente;
	}

	public void setTemperaturaAmbiente(double temperaturaAmbiente) {
		this.temperaturaAmbiente = temperaturaAmbiente;
	}
	
	public void setTemperaturaActual(int nuevaTemperatura) {
		temperaturaActual = nuevaTemperatura;		
	}
	
	public void actualizarMediciones() {
		//De alguna manera sensa el ambiente y hace un setTemperatura() con la medición
	}
	
	public double medir() {
		return temperaturaAmbiente;
	}
	
	public boolean hayQueActuar(DispositivoInteligente dispositivo) {
		return dispositivo.hayQueActuar(this.temperaturaAmbiente);
	}

	public void registrarNuevaTempratura(int nuevaTemperatura) {
		this.setTemperaturaActual(nuevaTemperatura);
		
	}

}
