package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public class Apagado implements EstadoDelDispositivo {

	public void apagar(DispositivoInteligente dispositivo) {
	}

	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	}

	public boolean estaEncendido() {
		return false;
	}

	public double getIntensidad() {
		return 0;
	}

	// CON ESTOS DOS DEBERIA TIRAR UNA EXCEPCION //

	@Override
	public void setIntensidad(double nuevoValor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModo(ModoFrio_Calor nuevoModo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ModoFrio_Calor getModo() {
		// TODO Auto-generated method stub
		return null;
	}

}
