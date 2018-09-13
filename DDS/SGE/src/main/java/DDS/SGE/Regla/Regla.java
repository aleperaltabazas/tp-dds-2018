package DDS.SGE.Regla;

import DDS.SGE.Sensor.*;
import DDS.SGE.Actuador.*;
import DDS.SGE.Dispositivo.*;
import java.util.List;

import javax.persistence.*;

@Entity
public class Regla {

	@Id
	@GeneratedValue
	private Long id;

	// Many to many
	@Transient
	private List<Sensor> sensores;

	// Many to one, creo
	@Transient
	private Actuador actuador;

	public Regla(List<Sensor> sensores, Actuador actuador) {
		this.sensores = sensores;
		this.actuador = actuador;
	}

	public void actuar() {
		sensores.forEach(s -> s.actualizarMediciones());

		if (sensores.stream().allMatch(s -> s.hayQueActuar())) {
			actuador.accionar();
		}

	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public Actuador getActuador() {
		return actuador;
	}

	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
