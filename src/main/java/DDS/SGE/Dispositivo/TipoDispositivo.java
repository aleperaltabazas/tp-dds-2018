package DDS.SGE.Dispositivo;

import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TipoDispositivo {

	@Id
	@GeneratedValue
	private Long id;

	public abstract boolean estaEncendido();
	public abstract long usoEstimadoDiario();

	public abstract double tiempoTotalEncendidoHaceNHoras(int horas);
	public abstract double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo);

	public abstract double getConsumoKWPorHora();
	public abstract TipoDispositivo adaptar();

	public abstract void encender();
	public abstract void apagar();
	
	public abstract void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos);
	public abstract void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones);

	public abstract double usoMensualMinimo();
	public abstract double usoMensualMaximo();
}
