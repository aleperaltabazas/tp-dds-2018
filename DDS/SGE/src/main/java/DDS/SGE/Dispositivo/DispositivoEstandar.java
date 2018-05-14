package DDS.SGE.Dispositivo;

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
}
