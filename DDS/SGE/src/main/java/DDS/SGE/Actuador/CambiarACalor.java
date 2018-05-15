package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.ModoFrio_Calor;

public class CambiarACalor implements Actuador {

	@Override
	public void accionarSobre(DispositivoInteligente dispositivo) {
		dispositivo.getEstado().setModo(ModoFrio_Calor.CALOR);
	}

}
