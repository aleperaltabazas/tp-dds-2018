package DDS.SGE.Dispositivo;

import org.json.JSONObject;

import DDS.SGE.Dispositivo.Estado.Encendido;

public class Dispositivo {
	private String nombre;
	private double consumoKWPorHora;
	private TipoDispositivo tipo;

	public Dispositivo(double consumoKWPorHora, TipoDispositivo tipo) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.tipo = tipo;
	}
	
	public TipoDispositivo getTipoDispositivo() {
		return this.tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean estaEncendido() {
		return tipo.estaEncendido();
	}

	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	public double consumoDiarioEstimado() {
		return consumoKWPorHora * tipo.usoEstimadoDiario();
	}
	
	//Implementar, por lo que entendi del encunciado mediante esto el dispositivo se apaga o prende
	public void recibirActuador(Actuador unActuador) {
		//Opcion para que no haga nada con los estandar seria lanzar una excepcion
		//Generar efecto sobre el DI
	}

}
