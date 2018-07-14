package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Movimiento implements Sensor {
	DispositivoInteligente dispositivo;
	// Creado para implementar una interfaz con el ENRE
	boolean movimientoDetectado = false;
	
	public void setMovimientoDetectado(boolean value) {
		movimientoDetectado = value;
	}
	
	@Override
	public void actualizarMediciones() {
		
	}

	@Override
	public double medir() {
		return 0;
	}

	public void registrarMovimiento() {
		this.setMovimientoDetectado(true);		
	}

	@Override
	public boolean hayQueActuar() {
		return false;
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		return null;
	}

}
