package DDS.SGE.Sensor;

public class Luz extends Sensor {
	double intensidadLuminicaAmbiente;
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
	
	@Override
	public void actualizarMediciones() {
		////De alguna manera sensa el ambiente y hace un setIntensidadLuminica() con la medición
	}
	
	@Override
	public double medir() {
		return this.intensidadLuminicaAmbiente;		
	}
	
	public void registrarNuevaIntensidad(int nuevaIntensidad) {
		this.setLuzActual(nuevaIntensidad);		
	}
	
	@Override
	public boolean hayQueActuar() {
		return true;
	}

}
