package Fabricante;

public class Lampara implements Fabricante {

	int usoMensualMinimo = 90;
	int usoMensualMaximo = 360;
	int watts;
	Boolean halogenas
	
	public Lampara(int watts, Boolean halogenas) {
		this.watts = watts;
		this.halogenas = halogenas;
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
