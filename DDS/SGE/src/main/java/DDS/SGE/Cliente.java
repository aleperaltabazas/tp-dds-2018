package DDS.SGE;

import org.json.*;

public class Cliente {
	JSONObject obj = new JSONObject("{\r\n" + 
		"	\"nombre\": \"gonza\",\r\n" + 
		"	\"apellido\": \"vaquero\",\r\n" + 
		"	\"tipoDni\": \"DNI\",\r\n" + 
		"	\"numeroDocumento\"; \"666\",\r\n" + 
		"	\"telefono\": \"12345678\",\r\n" + 
		"	\"domicilio\": \"calle false 123\",\r\n" + 
		"	\"fecha\": \"14/06/2300\",\r\n" + 
		"	\"categoria\": \"R1\"\r\n" + /// La variable debería ser del tipo category categoria (ENUM)
		"}");
	String nombre = obj.getJSONObject("nombre").getString("nombre");	
	
	public void setCategoria(category nuevaCategoria) {
		categoria = nuevaCategoria; /// Agregar variable
	}
	
	/// PARA QUE DEVUELVA LA SUMA DE LOS CONSUMOS DE TODOS SUS DISPOSITIVOS
	public double consumoTotalPorHora() {
		///...
		return 1; /// Desarrollar
	};
}