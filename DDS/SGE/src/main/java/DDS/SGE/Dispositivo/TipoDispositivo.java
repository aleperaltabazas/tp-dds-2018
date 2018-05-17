package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;

public interface TipoDispositivo {
	boolean estaEncendido();

	float usoEstimadoDiario();
	
	TipoDispositivo adaptar();
	
	void encender();	
	void apagar();	
	void agregado(Cliente unCliente);
}
