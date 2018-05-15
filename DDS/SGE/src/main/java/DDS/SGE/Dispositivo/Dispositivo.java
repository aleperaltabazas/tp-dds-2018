package DDS.SGE.Dispositivo;

import org.json.JSONObject;

import DDS.SGE.Dispositivo.Estado.Encendido;

public abstract class Dispositivo {
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

}
