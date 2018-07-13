package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import DDS.SGE.Cliente;

public class Zona {

	double radio;
	List<Transformador> transformadores = new ArrayList<Transformador>();
	
	public Zona() {
		radio = 100;
	}
	
	public List<Transformador> getTransformadores() {
		return transformadores;
	}
	
	public void agregarTransformadores(List<Transformador> nuevosTransformadores) {
		this.getTransformadores().addAll(nuevosTransformadores);		
	}
	
	public double consumoTotal() {
		return this.getTransformadores().stream().mapToDouble(transformador -> transformador.suministra()).sum();
	}
	
	// Para mostrar sus usuarios -> Preguntar Gastón
	/*public ArrayList<Cliente> getClientesEnLaZona() {
		return (this.getTransformadores().stream().flatMap(transformador -> transformador.getUsuarios()));
	}*/
}
