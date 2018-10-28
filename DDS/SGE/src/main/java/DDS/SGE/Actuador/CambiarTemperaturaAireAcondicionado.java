package DDS.SGE.Actuador;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import DDS.SGE.Dispositivo.DispositivoInteligente;

@Entity
public class CambiarTemperaturaAireAcondicionado extends Actuador{
	
	@OneToOne
	DispositivoInteligente dispositivo;
	
	protected CambiarTemperaturaAireAcondicionado() {}
	
	public CambiarTemperaturaAireAcondicionado(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	@Override
	public void accionar(){
		dispositivo.getFabricante().actuar();
	}
	
	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}

