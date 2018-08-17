package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Subir_Intensidad implements Actuador {

	DispositivoInteligente dispositivo;
	
	double intensidadASubir;
	
	public Subir_Intensidad(double laIntensidad) {
		this.intensidadASubir = laIntensidad;
	}

	@Override
	public void accionar() {
		dispositivo.setIntensidad(dispositivo.getIntensidad() + intensidadASubir);
	}
	
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}
	
}
