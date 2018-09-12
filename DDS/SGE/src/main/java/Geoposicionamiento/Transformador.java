package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import DDS.SGE.Cliente;

@Entity
public class Transformador {

    @Id
    @GeneratedValue    
    private Long id;

	@OneToMany()
	@JoinColumn(name="id")
	List<Cliente> usuarios = new ArrayList<Cliente>();
	
	double energia;
	boolean activo;
	Zona zona;
	// List<Transformador> tr ; ---- NO SÉ PARA QUÉ ESTÁ

	public Transformador(Zona ubicacion) {
		zona = ubicacion;
		activo = true;
	}

	public Zona getZona() {
		return zona;
	}

	public List<Cliente> getUsuarios() {
		return usuarios;
	}

	public boolean estaActivo() {
		return activo;
	}

	public void agregarCliente(Cliente nuevoUsuario) {
		this.getUsuarios().add(nuevoUsuario);
	}

	public double suministra() {
		return usuarios.stream().mapToDouble(cliente -> cliente.consumoTotalEstimadoDiario()).sum();
	}

	public boolean perteneceA(Zona unaZona) {
		return unaZona == this.getZona();
	}

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
