package Fabricante;

import javax.persistence.Entity;

@Entity
public class Microondas extends Fabricante {

	int usoMensualMinimo = 3;
	int usoMensualMaximo = 15;
	
}
