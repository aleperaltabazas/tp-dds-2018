package Geoposicionamiento;

import java.awt.List;
import java.util.ArrayList;

public class Zona {

	int radio;
	ArrayList<Transformador> transformadores;
	
	public double consumoTotal() {
		return transformadores.stream().mapToDouble(transformador -> transformador.suministra()).sum();
	}
	
}
