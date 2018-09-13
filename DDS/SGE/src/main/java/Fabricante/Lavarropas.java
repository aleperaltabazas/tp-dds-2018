package Fabricante;

import javax.persistence.Entity;

@Entity
public class Lavarropas extends Fabricante {

	int kilos;
	Boolean automatico;
	Boolean calentamiento;
	
	public Lavarropas(int kilos, Boolean automatico, Boolean calentamiento) {
		this.kilos = kilos;
		this.automatico = automatico;
		this.calentamiento = calentamiento;
		this.consumoKWPorHora = 0.875;
		inicializarUsoMinimoYMaximo(6, 30);
	}
	
}
