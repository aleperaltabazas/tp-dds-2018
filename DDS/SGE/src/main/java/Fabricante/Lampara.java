package Fabricante;

public class Lampara implements Fabricante {

	int usoMensualMinimo = 90;
	int usoMensualMaximo = 360;
	int watts;
	Boolean halogenas;
	
	public Lampara(int watts, Boolean halogenas) {
		this.watts = watts;
		this.halogenas = halogenas;
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
		return usoMensualMinimo;
	}

	@Override
	public double usoMensualMaximo() {
		return usoMensualMaximo;
	}
}
