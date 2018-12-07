package DDS.SGE.Fabricante;

import javax.persistence.Entity;

@Entity
public class Ventilador extends Fabricante {

	Boolean deTecho;
	
	protected Ventilador() {}
	
	public Ventilador(Boolean deTecho) {
		this.deTecho = deTecho;
		this.consumoKWPorHora = 0.09;
		inicializarUsoMinimoYMaximo(120, 360);
	}
	
}
