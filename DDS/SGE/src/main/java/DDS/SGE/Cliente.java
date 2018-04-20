package DDS.SGE;

import java.util.List;

import org.json.*;

public class Cliente implements Usuario {
	String nombre;
	String apellido;
	String dni; // Creo que eran 4 posibilidades asi que puede ser un enum
	String numeroDni; // numeroDni y telefono deberian ser numericos?
	String telefono;
	String domicilio;
	String fecha; // Despues podria ver de hacerlo fecha
	Categoria categoria;
	List<Dispositivo> dispositivos;

	/// Creo uno simplificado para los tests
	public Cliente(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public void setCategoria(Categoria nuevaCategoria) {
		categoria = nuevaCategoria; /// Agregar variable
	}

	public boolean algunDispositivoEncendido() {
		return dispositivos.stream().anyMatch(dispositivo -> dispositivo.getEncendido());
	}

	public int dispositivosTotales() {
		return dispositivos.size();
	}

	public int dispositivosEncendidos() {
		return 1; // Desarrollar
	}

	public int dispositivosApagados() {
		return this.dispositivosTotales() - this.dispositivosEncendidos(); // Para no reutilizar logica
	}

	/// PARA QUE DEVUELVA LA SUMA DE LOS CONSUMOS DE TODOS SUS DISPOSITIVOS
	/// Puede que nos convenga calcular directo el consumo por mes estimado, que
	/// seria lo mismo *24 * dias del mes pero no sé exactamente
	public double consumoTotalPorHora() {
		/// ...
		return 1; /// Desarrollar
	}

	public void CargarDesdeJson(String json) {
		JSONObject obj = new JSONObject(json);
		nombre = obj.getString("nombre");
		apellido = obj.getString("apellido");
		dni = obj.getString("tipoDni");
		numeroDni = obj.getString("numeroDocumento");
		telefono = obj.getString("telefono");
		domicilio = obj.getString("domicilio");
		fecha = obj.getString("fecha");
		categoria = (Categoria) obj.get("categoria");
		// JSONObject obj = new
		// JSONObject("{nombre:gonzalo,apellido:vaquero,tipoDni:DNI,numeroDocumento:123,telefono:4444444,domicilio:calle
		// falsa 123, fecha:15-15-2030,categoria:R1}") ;

	};
}