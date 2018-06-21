package Geoposicionamiento;

import java.awt.List;
import java.util.ArrayList;

import DDS.SGE.Cliente;

public class Transformador {

	double energia;
	boolean activo;
	Zona zona;
	ArrayList<Cliente> usuarios; 
	
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
}
