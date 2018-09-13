package DDS.SGE.Actuador;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import DDS.SGE.Dispositivo.DispositivoInteligente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Actuador {

	public void accionar() {

	}

	public DispositivoInteligente getDispositivo() {
		return null;
	}

}
