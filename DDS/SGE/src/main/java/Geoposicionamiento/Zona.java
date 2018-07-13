package Geoposicionamiento;

import java.awt.List;
import java.util.ArrayList;

import DDS.SGE.Cliente;

public class Zona {

	int radio;
	ArrayList<Transformador> transformadores;
	
	public ArrayList<Transformador> getTransformadores() {
		return transformadores;
	}
	
	public double consumoTotal() {
		return transformadores.stream().mapToDouble(transformador -> transformador.suministra()).sum();
	}
	
}
