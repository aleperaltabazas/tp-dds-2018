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

	public double consumoTotal() {
		return this.getTransformadores().stream().mapToDouble(transformador -> transformador.suministra()).sum();
	}

	// PARA CUANDO RECIBE LISTA DEL ENRE
	public void agregarTransformadores(List<Transformador> nuevosTransformadores) {
		this.getTransformadores().addAll(nuevosTransformadores);
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

}
