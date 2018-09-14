package Fabricante;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Fabricante {

	@Id
	@GeneratedValue
	private Long id;

	double consumoKWPorHora;
	protected int usoMensualMinimo;
	protected int usoMensualMaximo;

	public void actuar() {
	}

	public double getConsumoKWPorHora() {
		return this.consumoKWPorHora;
	}

	public double usoMensualMinimo() {
		return this.usoMensualMinimo;
	}

	public double usoMensualMaximo() {
		return this.usoMensualMaximo;
	}

	public void inicializarUsoMinimoYMaximo(int usoMensualMinimo, int usoMensualMaximo) {
		this.usoMensualMinimo = usoMensualMinimo;
		this.usoMensualMaximo = usoMensualMaximo;
	}
}
