package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public class Apagado extends EstadoDelDispositivo {

	@Override
	public void apagar(DispositivoInteligente dispositivo) {
	}
	
	@Override
	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	}

	@Override
	public boolean estaEncendido() {
		return false;
	}
	
	@Override
	public double getIntensidad() {
		return 0;
	}

	// CON ESTOS DOS DEBERIA TIRAR UNA EXCEPCION //

	@Override
	public void setIntensidad(double nuevoValor) {

	}

	@Override
	public void setModo(ModoFrio_Calor nuevoModo) {

	}

	@Override
	public ModoFrio_Calor getModo() {
		// TODO Auto-generated method stub
		return null;
	}

}
