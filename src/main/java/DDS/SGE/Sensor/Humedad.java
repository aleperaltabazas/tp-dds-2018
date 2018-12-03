package DDS.SGE.Sensor;

import javax.persistence.Entity;

@Entity
public class Humedad extends Sensor {
	double humedadActual;
	
	protected Humedad() {}
	
	public void setHumedadActual(int nuevaHumedad) {
		humedadActual = nuevaHumedad;		
	}
	
	public double getHumedadActual() {
		return humedadActual;
	}
	
	@Override
	public void actualizarMediciones() {
	
	}

	@Override
	public double medir() {
		return 0;
	}

	public void registrarNivelHumedad(int nuevaHumedad) {
		this.setHumedadActual(nuevaHumedad);
	}
	
	@Override
	public boolean hayQueActuar() {
		return true;
	}

}
