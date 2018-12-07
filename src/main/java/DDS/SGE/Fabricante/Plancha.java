package DDS.SGE.Fabricante;

import javax.persistence.Entity;

@Entity
public class Plancha extends Fabricante {
	
	protected Plancha() {}
	
	public Plancha(double consumo) {
		this.consumoKWPorHora = consumo;
		this.usoMensualMinimo = 3;
		this.usoMensualMaximo = 30;
	}
}
