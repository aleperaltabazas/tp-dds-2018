package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Subir_Intensidad implements Actuador {

	
	double intensidadASubir;

	@Override
	public void accionarSobre(DispositivoInteligente dispositivo) {
		dispositivo.setIntensidad(dispositivo.getIntensidad() + intensidadASubir);

	}
	
}
