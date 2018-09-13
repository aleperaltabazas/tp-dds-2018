package Fabricante;

import javax.persistence.Entity;

@Entity
public class Heladera extends Fabricante {

	Boolean freezer;
	
	public Heladera(Boolean freezer) {
		this.freezer = freezer;
		this.consumoKWPorHora = 0.09;
		inicializarUsoMinimoYMaximo(90, 360);
	}
}
