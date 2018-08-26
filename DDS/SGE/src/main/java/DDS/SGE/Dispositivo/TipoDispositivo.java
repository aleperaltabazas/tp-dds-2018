package DDS.SGE.Dispositivo;


import DDS.SGE.Cliente;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

import java.time.LocalDateTime;


public interface TipoDispositivo {
	boolean estaEncendido();

	long usoEstimadoDiario();
	
	public double tiempoTotalEncendidoHaceNHoras(int horas);
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo);
	public double getConsumoKWPorHora();
	
	TipoDispositivo adaptar();
	
	void encender();	
	void apagar();

	void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos);

	void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones);	
	
	public double usoMensualMinimo();
	public double usoMensualMaximo();
}
