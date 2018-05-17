package DDS.SGE.Dispositivo;

import java.time.LocalDateTime;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Estado.*;
//import DDS.SGE.Dispositivo.Estado.EstadoDelDispositivo;

public class DispositivoInteligente implements TipoDispositivo {
	EstadoDelDispositivo estado;
	RepositorioDeTiempoEncendido repositorio = new RepositorioDeTiempoEncendido();

	public DispositivoInteligente(EstadoDelDispositivo estado) {
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

	public double consumoDeNHoras(int horas) {
		return 42;
	}

	public TipoDispositivo adaptar() {
		//Aca podemos transformarlo a estandar (como esta hecho), hacer un return this; o tirar una excepcion porque no se puede adaptar un Inteligente
		//Como el enunciado no lo aclara, decidí que si agregas el modulo adaptador al Inteligente, lo convertis en un Estandar (auqnue no tenga mucho sentido funcionalmente)
		return new DispositivoEstandar(this.usoEstimadoDiario()); 
	}

	public void agregado(Cliente unCliente) {
		unCliente.sumarPuntos(15);		
	}
}
