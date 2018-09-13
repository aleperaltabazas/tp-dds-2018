package DDS.SGE.Sensor;

import javax.persistence.*;

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
