package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Zona {

	@Id
	@GeneratedValue
	private Long id;

	@Transient
	private ActualizadorDeTransformadores actualizador = new ActualizadorDeTransformadores();

	@OneToMany()
	@JoinColumn(nullable = true, name = "id")
	@Transient
	List<Transformador> transformadores = new ArrayList<Transformador>();

	Long latitudCentro;
	Long longitudCentro;
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

	public void agregarTransformadores(List<Transformador> nuevosTransformadores) {
		this.transformadores.addAll(nuevosTransformadores);
	}

	/*
	 * PARA CUANDO RECIBE LISTA DEL ENRE public void
	 * agregarListaEnre(List<Transformador> nuevosTransformadores) {
	 * this.actualizador.RecibirMensajeDelENRE(nuevosTransformadores); }
	 */

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

}
