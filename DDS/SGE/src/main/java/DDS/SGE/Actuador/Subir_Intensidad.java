package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Subir_Intensidad extends Actuador {

	DispositivoInteligente dispositivo;

	double intensidadASubir;

	public Subir_Intensidad(double laIntensidad) {
		this.intensidadASubir = laIntensidad;
	}

	@Override
	public void accionar() {
		dispositivo.setIntensidad(dispositivo.getIntensidad() + intensidadASubir);
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
