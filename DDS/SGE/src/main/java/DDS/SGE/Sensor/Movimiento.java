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
		// TODO Auto-generated method stub
		
	}

	@Override
	public double medir() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void registrarMovimiento() {
		this.setMovimientoDetectado(true);		
	}

	@Override
	public boolean hayQueActuar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		// TODO Auto-generated method stub
		return null;
	}

}
