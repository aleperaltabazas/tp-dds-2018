package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

public interface TipoDispositivo {
	boolean estaEncendido();

	long usoEstimadoDiario();
	
	public double tiempoTotalEncendidoHaceNHoras(int horas);
	
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo);
	
	TipoDispositivo adaptar();
	
	void encender();
	
	void apagar();
}
