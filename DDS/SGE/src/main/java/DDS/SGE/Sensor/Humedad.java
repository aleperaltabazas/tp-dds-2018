package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Humedad implements Sensor {
	double humedadActual;
	
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
	
	public boolean hayQueActuar() {
		return true;
	}

}
