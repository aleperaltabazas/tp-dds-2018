package Fabricante;

public class DeMovimiento implements Fabricante {
	
	private Boolean seMueve = false;
	
	@Override
	public int Medir() {		
		return seMueve ? 1 : 0;
	}

	@Override
	public void Actuar() {
		this.seMueve = !(this.seMueve);

	}

}
