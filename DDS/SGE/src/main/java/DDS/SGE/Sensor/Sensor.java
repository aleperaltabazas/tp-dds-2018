package DDS.SGE.Sensor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sensor {

	@Id
	@GeneratedValue
	private Long id;

	public abstract void actualizarMediciones();
	
	public double medir() {
		return 0;
	}

	public boolean hayQueActuar() {
		return false;
	}
}
