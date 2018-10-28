package Fabricante;

import javax.persistence.Entity;

@Entity
public class Heladera extends Fabricante {

	Boolean freezer;
	
	protected Heladera() {}
	
	public Heladera(Boolean freezer) {
		this.freezer = freezer;
		this.consumoKWPorHora = 0.09;
		inicializarUsoMinimoYMaximo(90, 360);
	}
	
	public Heladera(double consumo, Boolean freezer) {
		this.freezer = freezer;
		this.consumoKWPorHora = consumo;
		inicializarUsoMinimoYMaximo(90, 360);
	}
}
