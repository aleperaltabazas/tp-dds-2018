package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Bajar_Intensidad extends Actuador {

	DispositivoInteligente dispositivo;
	double intensidadABajar;
	
	public Bajar_Intensidad(double intensidadABajar) {
		this.intensidadABajar = intensidadABajar;
	}

	@Override
	public void accionar() {
		dispositivo.setIntensidad(dispositivo.getIntensidad() - intensidadABajar);
	}
	
	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
