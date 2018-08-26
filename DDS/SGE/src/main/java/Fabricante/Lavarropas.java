package Fabricante;

public class Lavarropas implements Fabricante {

	int usoMensualMinimo = 6;
	int usoMensualMaximo = 30;
	int kilos;
	Boolean automatico;
	Boolean calentamiento;
	
	public Lavarropas(int kilos, Boolean automatico, Boolean calentamiento) {
		this.kilos = kilos;
		this.automatico = automatico;
		this.calentamiento = calentamiento;
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

	@Override
	public double getConsumoKWPorHora() {
		// TODO Auto-generated method stub
		return 0;
	}
}
