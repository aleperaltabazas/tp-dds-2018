package Fabricante;

public class AireAcondicionado implements Fabricante {

	int usoMensualMinimo = 90;
	int usoMensualMaximo = 360;
	int frigorias;
	
	public AireAcondicionado(int frigorias) {
		this.frigorias = frigorias;
	}
	
	@Override
	public int Medir() {
		return 0;
	}

	@Override
	public void Actuar() {
		// TODO Auto-generated method stub

	}

}
