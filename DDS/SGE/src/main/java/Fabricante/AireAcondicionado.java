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

	@Override
	public double usoMensualMinimo() {
		return usoMensualMinimo;
	}

	@Override
	public double usoMensualMaximo() {
		return usoMensualMaximo;
	}

	@Override
	public double getConsumoKWPorHora() {
		return 0;
	}
}
