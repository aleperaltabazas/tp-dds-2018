package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Bajar_Intensidad implements Actuador {

	double intensidadABajar;
	
	public Bajar_Intensidad(double intensidadABajar) {
		this.intensidadABajar = intensidadABajar;
	}

	@Override
	public void accionarSobre(DispositivoInteligente dispositivo) {
		dispositivo.setIntensidad(dispositivo.getIntensidad() - intensidadABajar);

	}

}
