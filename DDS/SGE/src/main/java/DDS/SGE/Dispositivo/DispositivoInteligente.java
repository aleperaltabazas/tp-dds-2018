package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.Estado.*;
//import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;

public class DispositivoInteligente extends Dispositivo {
	EstadoDelDispositivo estado;
	RepositorioDeTiempoEncendido repositorio = new RepositorioDeTiempoEncendido();

	public DispositivoInteligente(double consumoKWPorHora, TipoDispositivo tipo, EstadoDelDispositivo estado) {
		super(consumoKWPorHora, tipo);
		this.estado = estado;
	}

	// Falta implementacion
	public float usoEstimadoDiario() {
		return 1;
	}

	public boolean estaEncendido() {
		return estado.estaEncendido();
	}

	boolean estaApagado() {
		return !estado.estaEncendido();
	}

	public EstadoDelDispositivo getEstado() {
		return estado;
	}

	public void encender() {
		this.estado.encender(this);
		repositorio.encender(LocalDateTime.now());
	}

	public void apagar() {
		this.estado.apagar(this);
		repositorio.apagar(LocalDateTime.now());
	}

	public void ahorraEnergia() {
		this.estado.ahorraEnergia(this);
	}

	public double getIntensidad() {
		return this.estado.getIntensidad();
	}

	public void setIntensidad(double nuevoValor) {
		this.estado.setIntensidad(nuevoValor);
	}

	public void setEstado(EstadoDelDispositivo unEstado) {
		this.estado = unEstado;
	}

	public double consumoDeNHoras(int horas) {
		return 42;
	}

}
