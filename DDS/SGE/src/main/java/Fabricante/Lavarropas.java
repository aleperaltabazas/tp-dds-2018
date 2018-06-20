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
	public int Medir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Actuar() {
		// TODO Auto-generated method stub

	}

}
