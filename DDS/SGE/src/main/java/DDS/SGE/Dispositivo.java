package DDS.SGE;

import org.json.JSONObject;

public class Dispositivo {
	private String nombre;
	private double consumoKWPorHora;
	private TipoDispositivo tipo;

	public Dispositivo(double consumoKWPorHora, TipoDispositivo tipo) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.tipo = tipo;
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
	
	/*
	public void convertirAInteligente(Adaptador adaptador) {
		//validar si ya es inteligente o no
		//this.tipo = new DispositivoInteligente(adaptador., adaptador.);
		//Agregar 10 puntos al cliente
	}
	*/
}
