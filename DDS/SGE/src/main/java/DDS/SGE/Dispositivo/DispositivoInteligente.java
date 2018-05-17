package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DDS.SGE.Dispositivo.Estado.*;
//import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;

public class DispositivoInteligente implements TipoDispositivo {
	EstadoDelDispositivo estado;
	//Queda muy feo pero si no tengo un constructor asi no se como testear
	RepositorioDeTiempoEncendido repositorio = new RepositorioDeTiempoEncendido(new ArrayList<IntervaloActivo>());

	public DispositivoInteligente(EstadoDelDispositivo estado) {
		this.estado = estado;
	}

	// Falta implementacion
	// Estaria bien pensarlo asi?
	public long usoEstimadoDiario() {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(24);
	}

	public boolean estaEncendido() {
		return estado.estaEncendido();
	}

	boolean estaApagado() {
		return !this.estaEncendido();
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

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		return repositorio.tiempoTotalEncendidoHaceNHorasEnMinutos(horas);
	}
	
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return repositorio.tiempoTotalEnUnPeriodoEnMinutos(principioPeriodo, finPeriodo);
	}

	@Override
	public TipoDispositivo adaptar() {
		//Aca podemos transformarlo a estandar (como esta hecho), hacer un return this; o tirar una excepcion porque no se puede adaptar un Inteligente
		//Como el enunciado no lo aclara, decidí que si agregas el modulo adaptador al Inteligente, lo convertis en un Estandar (auqnue no tenga mucho sentido funcionalmente)
		return new DispositivoEstandar(this.usoEstimadoDiario()); 
	}

}
