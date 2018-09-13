package Fabricante;

import javax.persistence.Entity;

@Entity
public class Plancha extends Fabricante {

	int usoMensualMinimo = 3;
	int usoMensualMaximo = 30;
}
