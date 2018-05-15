package DDS.SGE.Dispositivo.Estado;

public interface EstadoDelDispositivo {
	boolean estaEncendido();

	double getIntensidad();
	void setIntensidad(double nuevoValor);
	void setModo(ModoFrio_Calor nuevoModo);
}
