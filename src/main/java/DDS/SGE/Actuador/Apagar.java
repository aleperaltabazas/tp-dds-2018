package DDS.SGE.Actuador;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import DDS.SGE.Dispositivo.DispositivoInteligente;

@Entity
public class Apagar extends Actuador {

	@OneToOne
	DispositivoInteligente dispositivo;
	
	protected Apagar() {};
	
	public Apagar(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	@Override
	public void accionar() {
		dispositivo.apagar();
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
