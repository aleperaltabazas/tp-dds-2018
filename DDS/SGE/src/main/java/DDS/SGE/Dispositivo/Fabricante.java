package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

public interface Fabricante {
	public boolean estaEncendido();
	public long usoEstimadoDiario();
	public double tiempoTotalEncendidoHaceNHoras(int horas);
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo);
	public TipoDispositivo adaptar();
	public void encender();
	public void apagar();
	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos);
	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones);
}
