package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Entity;

import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Computadora;
import Fabricante.Fabricante;

@Entity
public class DispositivoEstandar extends TipoDispositivo {

	long usoEstimadoDiario;
	double consumoKWPorHora;

	public DispositivoEstandar(long usoEstimadoDiario, double consumoKWPorHora) {
		this.usoEstimadoDiario = usoEstimadoDiario;
		this.consumoKWPorHora = consumoKWPorHora;
	}

	@Override
	public boolean estaEncendido() {
		return false;
	}

	@Override
	public long usoEstimadoDiario() {
		return usoEstimadoDiario;
	}

	@Override
	public TipoDispositivo adaptar() {
		return new DispositivoInteligente(new Apagado(), new Computadora(true));
	}

	@Override
	public void encender() {
		// No hace nada.
	}

	@Override
	public void apagar() {
		// No hace nada.
	}

	@Override
	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	@Override
	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return 0;
	}

	@Override
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return 0;
	}

	@Override
	public double usoMensualMinimo() {
		return 0;
	}

	@Override
	public double usoMensualMaximo() {
		return 0;
	}

	@Override
	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		// No hace nada
	}

	@Override
	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		// No hace nada
	}

}
