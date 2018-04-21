package DDS.SGE;

import org.json.JSONObject;


public class Dispositivo {
	public String nombre;
	private double consumoKWPorHora;
	private boolean encendido;

	// Lo hago simplificado para los tests
	public Dispositivo(double consumoKWPorHora, boolean encendido) {
		this.consumoKWPorHora = consumoKWPorHora;
		this.encendido = encendido;
	}

	// Es un getter, pero me parece que sería mas expresivo ponerle estaEncendido()
	// sobre todo al llamarlo desde los métodos del cliente. Igual creo que debemos
	// respetar en mayor medida la convención
	public void cargarDesdeJson(String json) {
		JSONObject obj = new JSONObject(json);	
		nombre = obj.getString("nombre");
		consumoKWPorHora = obj.getDouble("consumoKWPorHora");
		encendido = obj.getBoolean("encendido");
	}
	
	public boolean estaEncendido() {
		return this.encendido;
	}

	// Idem que el caso anterior
	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}
}
