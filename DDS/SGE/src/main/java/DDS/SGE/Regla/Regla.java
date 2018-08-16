package DDS.SGE.Regla;

import DDS.SGE.Sensor.*;
import DDS.SGE.Actuador.*;
import DDS.SGE.Dispositivo.*;
import java.util.List;

public class Regla {

	private List<Sensor> sensores;
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

}
