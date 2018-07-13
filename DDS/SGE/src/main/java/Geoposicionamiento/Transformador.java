package Geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import DDS.SGE.Cliente;

public class Transformador {

	double energia;
	boolean activo;
	Zona zona;
	ArrayList<Cliente> usuarios = new ArrayList<Cliente>(); 
	List<Transformador> tr ;
	
	public Transformador(Zona ubicacion) {
		zona = ubicacion;
		activo = true;
	}
	
	public Zona getZona() {
		return zona;
	}
	public ArrayList<Cliente> getUsuarios(){
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
}
