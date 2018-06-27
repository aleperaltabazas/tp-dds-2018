package Fabricante;

public class Heladera implements Fabricante {

	Boolean freezer;
	
	public Heladera(Boolean freezer) {
		this.freezer = freezer;
	}
	
	@Override
	public int medir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actuar() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hayQueActuar(double temperatura) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double usoMensualMinimo() {
		return 0;
	}

	@Override
	public double usoMensualMaximo() {
		return 0;
	}
}
