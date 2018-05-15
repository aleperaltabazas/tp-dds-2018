package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public class Apagado implements EstadoDelDispositivo {

	public Apagado() {
	}

	public void apagar(DispositivoInteligente dispositivo) {
	}

	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	public void ahorraEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia());
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

}
