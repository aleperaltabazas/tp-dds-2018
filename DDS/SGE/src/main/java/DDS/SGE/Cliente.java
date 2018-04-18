package DDS.SGE;

import org.json.*;

public class Cliente implements Usuario {
	String nombre;
	String apellido;
	String dni; //Creo que eran 4 posibilidades asi que puede ser un enum
	String numeroDni; //numeroDni y telefono deberian ser numericos?
	String telefono;
	String domicilio;
	String fecha; //Despues podria ver de hacerlo fecha
	category categoria;
	
	public void setCategoria(category nuevaCategoria) {
		categoria = nuevaCategoria; /// Agregar variable
	}
	
	/// PARA QUE DEVUELVA LA SUMA DE LOS CONSUMOS DE TODOS SUS DISPOSITIVOS
	public double consumoTotalPorHora() {
		///...
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
		categoria = (category) obj.get("categoria");		
//JSONObject obj = new JSONObject("{nombre:gonzalo,apellido:vaquero,tipoDni:DNI,numeroDocumento:123,telefono:4444444,domicilio:calle falsa 123, fecha:15-15-2030,categoria:R1}") ;
		
	};
}