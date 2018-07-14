package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;
import java.time.LocalDateTime;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.Fabricante;

public class DispositivoEstandar implements TipoDispositivo {
	long usoEstimadoDiario;

	public DispositivoEstandar(long usoEstimadoDiario) {
		this.usoEstimadoDiario = usoEstimadoDiario;
	}

	public boolean estaEncendido() {
		return false;
	}

	public long usoEstimadoDiario() {
		return usoEstimadoDiario;
	}

	public TipoDispositivo adaptar() {
		return new DispositivoInteligente(new Apagado());
	}

	public void encender() {
		// No hace nada.
	}

	public void apagar() {
		// No hace nada.
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return 0;
	}

	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return 0;
	}

	@Override
	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		// No hace nada
		
	}

	@Override
	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		//No hace nada
	}
}
