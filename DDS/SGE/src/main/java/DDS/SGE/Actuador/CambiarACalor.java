package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;

public class CambiarACalor implements Actuador {

	DispositivoInteligente dispositivo;
	
	@Override
	public void accionar() {
		dispositivo.getEstado().setModo(ModoFrio_Calor.CALOR);
	}
	
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
