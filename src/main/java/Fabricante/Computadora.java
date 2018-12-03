package Fabricante;

import javax.persistence.Entity;

@Entity
public class Computadora extends Fabricante {

	Boolean notebook;
	
	protected Computadora() {}
	
	public Computadora(Boolean notebook) {
		this.notebook = notebook;
		this.consumoKWPorHora = 0.4;
		inicializarUsoMinimoYMaximo(60, 360);
	}
}
