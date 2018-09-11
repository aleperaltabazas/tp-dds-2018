package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public abstract class EstadoDelDispositivo {
	
	double intensidad;
	ModoFrio_Calor modo;
	
	public double getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(double nuevoValor) {
		intensidad = nuevoValor;
	}

	public void setModo(ModoFrio_Calor nuevoModo) {
		modo = nuevoModo;
	}
	
	public ModoFrio_Calor getModo() {
		return modo;
	}

	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Apagado());
	}
	
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}
	
	public void ahorraEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia());
	}
	
	public boolean estaEncendido() {
		return true;
	}
}
