package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Subir_Intensidad implements Actuador {

	
	double intensidadASubir;
	
	public Subir_Intensidad(double laIntensidad) {
		this.intensidadASubir = laIntensidad;
	}

	@Override
	public void accionarSobre(DispositivoInteligente dispositivo) {
		dispositivo.setIntensidad(dispositivo.getIntensidad() + intensidadASubir);

	}
	
}
