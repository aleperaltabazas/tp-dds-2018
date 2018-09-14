package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import DDS.SGE.Cliente;

@Entity
public class Transformador {

	@Id
	@GeneratedValue
	private Long id;

	private int codigo;

	@OneToMany()
	@JoinColumn(name = "id")
	@Transient
	List<Cliente> usuarios = new ArrayList<Cliente>();

	@Column(nullable = true)
	double energia;
	boolean activo;

	public Transformador(int i) {
		codigo = i;
		activo = true;
	}

	public Long getId() {
		return id;
	}

	public int getCodigo() {
		return codigo;
	}

	public List<Cliente> getUsuarios() {
		return usuarios;
	}

	public boolean estaActivo() {
		return activo;
	}

	public void agregarCliente(Cliente nuevoUsuario) {
		this.usuarios.add(nuevoUsuario);
	}

	public double suministra() {
		return usuarios.stream().mapToDouble(cliente -> cliente.consumoTotalEstimadoDiario()).sum();
	}

	/*
	 * public boolean perteneceA(Zona unaZona) { return unaZona == this.getZona(); }
	 */

	public double getEnergia() {
		return this.energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
