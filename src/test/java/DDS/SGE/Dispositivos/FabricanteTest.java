package DDS.SGE.Dispositivos;

import DDS.SGE.Fabricante.Fabricante;

public class FabricanteTest extends Fabricante{
	
	double consumoKWPorHora;
	
	public FabricanteTest(double consumoKWPorHora) {
		this.consumoKWPorHora = consumoKWPorHora;
		super.inicializarUsoMinimoYMaximo(60, 360);
	}
	
	public int medir() {
		return 0;
	}

	public double usoMensualMinimo() {
		return usoMensualMinimo;
	}

	public double usoMensualMaximo() {
		return usoMensualMaximo;
	}

	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

}
