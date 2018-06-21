package Fabricante;

public class AireAcondicionado implements Fabricante {

	int usoMensualMinimo = 90;
	int usoMensualMaximo = 360;
	int frigorias;
	
	double temperaturaDelDispositivo;
	double temperaturaRecomendada = 24;
	
	public AireAcondicionado(int frigorias) {
		this.frigorias = frigorias;
	}
	
	@Override
	public int medir() {
		return 0;
	}

	@Override
	public void actuar() {
		// TODO Auto-generated method stub
		this.ponerElAireEn(temperaturaRecomendada);
	}

	@Override
	public boolean hayQueActuar(double temperatura) {
		return temperatura < temperaturaRecomendada;
	}
	
	public double getTemperaturaDelDispositivo() {
		return this.temperaturaDelDispositivo;
	}

	public void ponerElAireEn(double temperatura) {
		this.temperaturaDelDispositivo = temperatura;
	}
}
