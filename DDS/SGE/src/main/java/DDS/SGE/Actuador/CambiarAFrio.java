package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;

public class CambiarAFrio implements Actuador {

	DispositivoInteligente dispositivo;
	
	@Override
	public void accionar() {
		dispositivo.getEstado().setModo(ModoFrio_Calor.FRIO);
	}
	
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
