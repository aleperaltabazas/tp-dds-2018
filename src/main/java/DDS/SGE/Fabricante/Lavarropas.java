package DDS.SGE.Fabricante;

import javax.persistence.Entity;

@Entity
public class Lavarropas extends Fabricante {

	int kilos;
	Boolean automatico;
	Boolean calentamiento;
	
	protected Lavarropas() {}
	
	public Lavarropas(int kilos, Boolean automatico, Boolean calentamiento) {
		this.kilos = kilos;
		this.automatico = automatico;
		this.calentamiento = calentamiento;
		this.consumoKWPorHora = 0.175;
		inicializarUsoMinimoYMaximo(6, 30);
	}
	
}
