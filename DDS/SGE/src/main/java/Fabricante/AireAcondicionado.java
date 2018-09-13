package Fabricante;

import javax.persistence.Entity;

@Entity
public class AireAcondicionado extends Fabricante {

	int frigorias;
	
	double temperaturaDelDispositivo;
	double temperaturaRecomendada = 24;
	
	public AireAcondicionado(int frigorias) {
		this.frigorias = frigorias;
		this.consumoKWPorHora = 1.013;
		inicializarUsoMinimoYMaximo(90, 360);
		this.temperaturaDelDispositivo = 0;
	}
	
	@Override
	public void actuar() {
		this.ponerElAireEn(temperaturaRecomendada);
	}

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
