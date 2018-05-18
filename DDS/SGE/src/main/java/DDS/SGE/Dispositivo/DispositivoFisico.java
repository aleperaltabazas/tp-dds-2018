package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

public class DispositivoFisico implements TipoDispositivo {

	Fabricante fabricante;
	
	public DispositivoFisico(Fabricante fabricante) {
		this.fabricante=fabricante;
	}
	
	public boolean estaEncendido() {
		return this.fabricante.estaEncendido();
	}

	public long usoEstimadoDiario() {
		return this.fabricante.usoEstimadoDiario();
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return this.fabricante.tiempoTotalEncendidoHaceNHoras(horas);
	}

	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return this.fabricante.tiempoTotalEncendidoEnUnPeriodo(principioPeriodo,finPeriodo);
	}

	public TipoDispositivo adaptar() {
		return this.fabricante.adaptar();
	}

	public void encender() {
		this.fabricante.encender();
	}

	public void apagar() {
		this.fabricante.apagar();
	}

	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		this.fabricante.seAgregoNuevoDispositivo(interesadoEnNuevosDispositivos);
	}

	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		this.fabricante.seAdaptoUnDispositivo(interesadoEnAdaptaciones);
	}
}
