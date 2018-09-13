package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;

public class CambiarACalor extends Actuador {

	DispositivoInteligente dispositivo;
	
	@Override
	public void accionar() {
		dispositivo.getEstado().setModo(ModoFrio_Calor.CALOR);
	}
	
	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
