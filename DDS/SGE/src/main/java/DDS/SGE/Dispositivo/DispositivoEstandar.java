package DDS.SGE.Dispositivo;

import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;

public class DispositivoEstandar implements TipoDispositivo{
	float usoEstimadoDiario;
	
	public DispositivoEstandar(float usoEstimadoDiario) {
		this.usoEstimadoDiario = usoEstimadoDiario;
	}
	
	public boolean estaEncendido() {
		return false;
	}
	
	public float usoEstimadoDiario() {
		return usoEstimadoDiario;
	}

	@Override
	public TipoDispositivo adaptar() {
		return new DispositivoInteligente(new Apagado()); // No se a que estado lo convierte.
	}

	@Override
	public void encender() {
		//No hace nada.		
	}

	@Override
	public void apagar() {
		// No hace nada.
		
	}
	
	
}
