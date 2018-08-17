package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Apagar implements Actuador {

	Dispositivo dispositivo;
	
	public Apagar(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	@Override
	public void accionar() {
		dispositivo.apagar();
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		//Esto queda por ver, quizas pueda pasarse el retorno a ser Dispositivo o TipoDispositivo 
		return null;
	}

}
