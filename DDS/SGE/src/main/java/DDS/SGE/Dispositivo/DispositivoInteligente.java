package DDS.SGE.Dispositivo;

import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;

public class DispositivoInteligente implements TipoDispositivo{
	boolean encendido;
	EstadoDelDispositivo estado;
	
	//Falta implementacion
	public float usoEstimadoDiario() {
		return 1;
	}
	
	public boolean estaEncendido() {
		return estado.estaEncendido();
	}
	
	public void encender() {
		this.encendido = true;
	}

	public void apagar() {
		this.encendido = false;
	}

}
