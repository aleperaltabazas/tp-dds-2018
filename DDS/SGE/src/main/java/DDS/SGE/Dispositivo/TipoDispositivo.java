package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

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

	boolean estaEncendido() {
		return false;
	}

	long usoEstimadoDiario() {
		return 0;
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return 0;
	}

	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return 0;
	}

	public double getConsumoKWPorHora() {
		return 0;
	}

	TipoDispositivo adaptar() {
		return null;
	}

	void encender() {

	}

	void apagar() {

	}

	void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {

	}

	void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {

	}

	public double usoMensualMinimo() {
		return 0;
	}

	public double usoMensualMaximo() {
		return 0;
	}
}
