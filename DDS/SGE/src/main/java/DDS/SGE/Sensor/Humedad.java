package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Humedad implements Sensor {
	DispositivoInteligente dispositivo;
	// Creado para implementar una interfaz con el ENRE
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

	@Override
	public boolean hayQueActuar() {
		return dispositivo.hayQueActuar(this.getHumedadActual());
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}
	

	public void registrarNivelHumedad(int nuevaHumedad) {
		this.setHumedadActual(nuevaHumedad);
	}

}
