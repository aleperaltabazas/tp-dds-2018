package DDS.SGE;

import org.json.JSONObject;

public class Dispositivo {
	private String nombre;
	private double consumoKWPorHora;
	private boolean encendido;

	// Lo hago simplificado para los tests
	public Dispositivo(double consumoKWPorHora, boolean encendido) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.encendido = encendido;
	}

	public void cargarDesdeJson(String json) {
		JSONObject obj = new JSONObject(json);

		nombre = obj.getString("nombre");
		consumoKWPorHora = obj.getDouble("consumoKWPorHora");
		encendido = obj.getBoolean("encendido");
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean estaEncendido() {
		return this.encendido;
	}

	// Idem que el caso anterior
	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	// Se podria pensar que cada vez que un cliente encienda o apague algun
	// dispositivo se recalcule su categoría
	public void encender() {
		this.encendido = true;
	}

	public void apagar() {
		this.encendido = false;
		// Si hacemos encender, hagamos apagar
	}
}
