package Fabricante;

public class Plancha implements Fabricante {

	int usoMensualMinimo = 3;
	int usoMensualMaximo = 30;
	
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
		return usoMensualMinimo;
	}

	@Override
	public double usoMensualMaximo() {
		return usoMensualMaximo;
	}
}
