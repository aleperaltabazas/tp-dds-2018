package Fabricante;

public class Ventilador extends Fabricante {

	Boolean deTecho;
	
	public Ventilador(Boolean deTecho) {
		this.deTecho = deTecho;
		this.consumoKWPorHora = 0.09;
		inicializarUsoMinimoYMaximo(120, 360);
	}
	
}
