package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Apagar implements Actuador {

	DispositivoInteligente dispositivo;
	
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
