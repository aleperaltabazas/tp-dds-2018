package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public abstract class Sensor {
	public void actualizarMediciones() {

	}

	public double medir() {
		return 0;
	}

	public boolean hayQueActuar() {
		return false;
	}
}
