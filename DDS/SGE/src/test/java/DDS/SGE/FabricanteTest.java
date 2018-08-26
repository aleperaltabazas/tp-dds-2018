package DDS.SGE;

import Fabricante.Fabricante;

public class FabricanteTest implements Fabricante{
	int usoMensualMinimo = 60;
	int usoMensualMaximo = 360;
	double consumoKWPorHora;
	
	public FabricanteTest(double consumoKWPorHora) {
		this.consumoKWPorHora = consumoKWPorHora;
	}
	
	public int medir() {
		return 0;
	}

	public void actuar() {
		
	}

	public boolean hayQueActuar(double temperatura) {
		return false;
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
