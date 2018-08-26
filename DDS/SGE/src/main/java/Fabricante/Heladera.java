package Fabricante;

public class Heladera implements Fabricante {

	Boolean freezer;
	
	public Heladera(Boolean freezer) {
		this.freezer = freezer;
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
		return 0;
	}

	public double usoMensualMaximo() {
		return 0;
	}

	@Override
	public double getConsumoKWPorHora() {
		// TODO Auto-generated method stub
		return 0;
	}
}
