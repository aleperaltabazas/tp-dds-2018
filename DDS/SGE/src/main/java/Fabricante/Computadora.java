package Fabricante;

public class Computadora implements Fabricante {

	int usoMensualMinimo = 60;
	int usoMensualMaximo = 360;
	Boolean notebook;
	
	public Computadora(Boolean notebook) {
		this.notebook = notebook;
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
