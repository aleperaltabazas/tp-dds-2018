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
	DispositivoInteligente dispositivo;
	double intensidadLuminicaAmbiente;
	// Creado para implementar una interfaz con el ENRE
	double luzActual;
	
	public Luz(double luzAmbiente) {
		this.intensidadLuminicaAmbiente = luzAmbiente;
	}
	
	public double getIntensidadLuminicaDelAmbiente() {
		return this.intensidadLuminicaAmbiente;
	}
	
	public void setLuzActual(int nuevaIntensidad) {
		luzActual = nuevaIntensidad;		
	}
	
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public void actualizarMediciones() {
		////De alguna manera sensa el ambiente y hace un setIntensidadLuminica() con la medición
	}
	
	public double medir() {
		return this.intensidadLuminicaAmbiente;		
	}
	
	public boolean hayQueActuar() {
		return dispositivo.hayQueActuar(this.intensidadLuminicaAmbiente);
	}

	public void registrarNuevaIntensidad(int nuevaIntensidad) {
		this.setLuzActual(nuevaIntensidad);		
	}


}
