package DDS.SGE.Dispositivo.Estado;

public abstract class ConEnergia implements EstadoDelDispositivo {
	double intensidad;
	ModoFrio_Calor modo;

	public boolean estaEncendido() {
		return true;
	}

	public double getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(double nuevoValor) {
		intensidad = nuevoValor;
	}

	public void setModo(ModoFrio_Calor nuevoModo) {
		modo = nuevoModo;
	}
}
