package Fabricante;

public class Ventilador implements Fabricante {

	int usoMensualMinimo = 120;
	int usoMensualMaximo = 360;
	Boolean deTecho;
	
	public Ventilador(Boolean deTecho) {
		this.deTecho = deTecho;
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
