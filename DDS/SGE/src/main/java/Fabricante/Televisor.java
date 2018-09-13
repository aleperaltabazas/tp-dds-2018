package Fabricante;

import javax.persistence.Entity;

@Entity
public class Televisor extends Fabricante {

	public enum TipoTelevisor {
		LED, LCD, TUBO
	}
	
	TipoTelevisor tipo;
	int pulgadas;

	public Televisor(int pulgadas,TipoTelevisor tipo) {
		this.pulgadas = pulgadas;
		this.tipo = tipo;
		this.consumoKWPorHora = 0.075;
		inicializarUsoMinimoYMaximo(90, 360);
	}
}
