package Fabricante;

import javax.persistence.Entity;

@Entity
public class Lampara extends Fabricante {
	
	int watts;
	Boolean halogenas;
	
	public Lampara(int watts, Boolean halogenas) {
		this.watts = watts;
		this.halogenas = halogenas;
		this.consumoKWPorHora = 0.04;
		inicializarUsoMinimoYMaximo(90, 360);
	}
	
}
