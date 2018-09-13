package DDS.SGE.Sensor;

public class Movimiento extends Sensor {
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
		return true;
	}

}
