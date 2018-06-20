package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Estado.*;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
//import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;
import Fabricante.*;

public class DispositivoInteligente implements TipoDispositivo {
	EstadoDelDispositivo estado;
	private double temperatura;
	Fabricante fabricante;
	
	RepositorioDeTiempoEncendido repositorio;

	public DispositivoInteligente(EstadoDelDispositivo estado) {
		this.estado = estado;
		repositorio = new RepositorioDeTiempoEncendido();
	}
	
	public EstadoDelDispositivo getEstado() {
		return estado;
	}
	
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double nuevaTemperatura) {
		this.temperatura = nuevaTemperatura;
	}
	
	public void setRepositorio(RepositorioDeTiempoEncendido repositorio) {
		this.repositorio = repositorio;
	}

	public long usoEstimadoDiario() {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(24);
	}

	public boolean estaEncendido() {
		return estado.estaEncendido();
	}

	boolean estaApagado() {
		return !this.estaEncendido();
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

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(horas);
	}
	
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return repositorio.tiempoTotalEnUnPeriodoEnMinutos(principioPeriodo, finPeriodo);
	}

	public TipoDispositivo adaptar() {
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

	public boolean hayQueActuar(double temperatura) {
		return fabricante.hayQueActuar(temperatura);
	}
}
