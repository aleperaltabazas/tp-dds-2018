package Fabricante;

public class Computadora extends Fabricante {

	Boolean notebook;
	
	public Computadora(Boolean notebook) {
		this.notebook = notebook;
		this.consumoKWPorHora = 0.4;
		inicializarUsoMinimoYMaximo(60, 360);
	}
}
