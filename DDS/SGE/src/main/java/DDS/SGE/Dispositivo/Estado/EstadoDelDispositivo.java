package DDS.SGE.Dispositivo.Estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import DDS.SGE.Dispositivo.*;

@Entity
public abstract class EstadoDelDispositivo {

	@Id
	@GeneratedValue
	private Long id;

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
