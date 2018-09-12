package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import DDS.SGE.Cliente;

@Entity
public class Zona {

    @Id
    @GeneratedValue
    private Long id;
    
	@OneToMany()
	@JoinColumn(name="id")
	List<Transformador> transformadores = new ArrayList<Transformador>();
	
	double radio;

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
