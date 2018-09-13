package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;

public class CambiarAFrio extends Actuador {

	DispositivoInteligente dispositivo;
	
	@Override
	public void accionar() {
		dispositivo.getEstado().setModo(ModoFrio_Calor.FRIO);
	}
	
	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
