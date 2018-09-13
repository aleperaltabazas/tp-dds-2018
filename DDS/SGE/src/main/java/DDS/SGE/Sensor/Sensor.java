package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import javax.persistence.*;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public abstract class Sensor {

	@Id
	@GeneratedValue
	private Long id;

	public void actualizarMediciones() {

	}

	public double medir() {
		return 0;
	}

	public boolean hayQueActuar() {
		return false;
	}
}
