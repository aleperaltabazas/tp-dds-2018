package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Movimiento implements Sensor {
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

}
