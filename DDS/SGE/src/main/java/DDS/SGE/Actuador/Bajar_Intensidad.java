package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Bajar_Intensidad implements Actuador {

	DispositivoInteligente dispositivo;
	double intensidadABajar;
	
	public Bajar_Intensidad(double intensidadABajar) {
		this.intensidadABajar = intensidadABajar;
	}

	@Override
	public void accionar() {
		dispositivo.setIntensidad(dispositivo.getIntensidad() - intensidadABajar);
	}
	
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}
