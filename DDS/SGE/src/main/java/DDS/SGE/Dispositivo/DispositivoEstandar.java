package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;
import java.time.LocalDateTime;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Computadora;
import Fabricante.Fabricante;

public class DispositivoEstandar implements TipoDispositivo {
	long usoEstimadoDiario;
	double consumoKWPorHora;

	public DispositivoEstandar(long usoEstimadoDiario, double consumoKWPorHora) {
		this.usoEstimadoDiario = usoEstimadoDiario;
		this.consumoKWPorHora = consumoKWPorHora;
	}

	public boolean estaEncendido() {
		return false;
	}

	public long usoEstimadoDiario() {
		return usoEstimadoDiario;
	}

	public TipoDispositivo adaptar() {
		return new DispositivoInteligente(new Apagado(), new Computadora(true));
	}

	public void encender() {
		// No hace nada.
	}

	public void apagar() {
		// No hace nada.
	}
	
	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return 0;
	}

	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return 0;
	}
	
	public double usoMensualMinimo() {
		return 0;
	}
	
	public double usoMensualMaximo() {
		return 0;
	}

	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		// No hace nada
	}

	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		//No hace nada
	}
}
