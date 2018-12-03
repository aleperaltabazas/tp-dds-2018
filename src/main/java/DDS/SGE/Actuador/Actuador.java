package DDS.SGE.Actuador;

import javax.persistence.*;

import DDS.SGE.Dispositivo.DispositivoInteligente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Actuador {

	@Id
	@GeneratedValue
	private Long id;

	public abstract void accionar();
	public abstract DispositivoInteligente getDispositivo();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
