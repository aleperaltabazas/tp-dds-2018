package DDS.SGE;

import org.json.*;

public class Cliente {
	category categoria;
	
	public void setCategoria(category nuevaCategoria) {
		categoria = nuevaCategoria; /// Agregar variable
	}
	
	/// PARA QUE DEVUELVA LA SUMA DE LOS CONSUMOS DE TODOS SUS DISPOSITIVOS
	public double consumoTotalPorHora() {
		///...
		return 1; /// Desarrollar
	};
}