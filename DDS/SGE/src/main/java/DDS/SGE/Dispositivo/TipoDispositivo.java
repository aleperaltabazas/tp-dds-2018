package DDS.SGE.Dispositivo;

public interface TipoDispositivo {
	boolean estaEncendido();

	float usoEstimadoDiario();
	
	TipoDispositivo adaptar();
	
	void encender();
	
	void apagar();
}
