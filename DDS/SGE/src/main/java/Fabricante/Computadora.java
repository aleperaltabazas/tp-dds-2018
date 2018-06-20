package Fabricante;

public class Computadora implements Fabricante {

	int usoMensualMinimo = 60;
	int usoMensualMaximo = 360;
	Boolean notebook;
	
	public Computadora(Boolean notebook) {
		this.notebook = notebook;
	}
	
	@Override
	public int Medir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Actuar() {
		// TODO Auto-generated method stub

	}

}
