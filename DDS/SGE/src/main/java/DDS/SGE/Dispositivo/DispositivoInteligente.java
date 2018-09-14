package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import DDS.SGE.Dispositivo.Estado.*;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
//import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.*;

@Entity
public class DispositivoInteligente extends TipoDispositivo {

	@ManyToOne()
	Fabricante fabricante;

	@OneToOne()
	EstadoDelDispositivo estado;

	@Embedded
	RepositorioDeTiempoEncendido repositorio;

	public DispositivoInteligente(EstadoDelDispositivo estado, Fabricante fabricante) {
		this.estado = estado;
		this.fabricante = fabricante;
		repositorio = new RepositorioDeTiempoEncendido();
	}

	public EstadoDelDispositivo getEstado() {
		return estado;
	}

	public RepositorioDeTiempoEncendido getRepositorioTiempoEncendido() {
		return this.repositorio;
	}

	@Override
	public double getConsumoKWPorHora() {
		return this.fabricante.getConsumoKWPorHora();
	}

	public void setFabricante(Fabricante unFabricante) {
		this.fabricante = unFabricante;
	}

	public void setRepositorio(RepositorioDeTiempoEncendido repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public long usoEstimadoDiario() {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(24);
	}

	@Override
	public boolean estaEncendido() {
		return estado.estaEncendido();
	}

	boolean estaApagado() {
		return !this.estaEncendido();
	}

	@Override
	public void encender() {
		this.estado.encender(this);
	}

	@Override
	public void apagar() {
		this.estado.apagar(this);
	}

	public void ahorraEnergia() {
		this.estado.ahorraEnergia(this);
	}

	public double getIntensidad() {
		return this.estado.getIntensidad();
	}

	public Fabricante getFabricante() {
		return this.fabricante;
	}

	public void setIntensidad(double nuevoValor) {
		this.estado.setIntensidad(nuevoValor);
	}

	public void setEstado(EstadoDelDispositivo unEstado) {
		this.estado = unEstado;
	}

	@Override
	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(horas);
	}

	@Override
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return repositorio.tiempoTotalEnUnPeriodoEnMinutos(principioPeriodo, finPeriodo);
	}

	public TipoDispositivo adaptar(Fabricante unFabricante) {
		return this;
	}

	@Override
	public void seAgregoNuevoDispositivo(InteresadoEnNuevosDispositivos interesadoEnNuevosDispositivos) {
		interesadoEnNuevosDispositivos.sumarPuntos();
	}

	@Override
	public void seAdaptoUnDispositivo(InteresadoEnAdaptaciones interesadoEnAdaptaciones) {
		interesadoEnAdaptaciones.sumarPuntos();
	}

	@Override
	public TipoDispositivo adaptar() {
		return this;
	}

	@Override
	public double usoMensualMinimo() {
		return fabricante.usoMensualMinimo();
	}

	@Override
	public double usoMensualMaximo() {
		return fabricante.usoMensualMaximo();
	}
}
