package DDS.SGE;

public enum Categoria {
	R1(0.644, 18.76, 0, 150), R2(0.644, 35.32, 150, 325), R3(0.681, 60.71, 325, 400), R4(0.738, 71.74, 400, 450), R5(
			0.794, 110.38, 450, 500), R6(0.832, 220.75, 500, 600), R7(0.851, 443.59, 600,
					700), R8(0.851, 545.96, 700, 1400), R9(0.851, 887.19, 1400, Double.POSITIVE_INFINITY);

	private double normalVariable;
	private double normalMensual;
	private double consumoMensualMaximo;
	private double consumoMensualMinimo;

	private Categoria(double tarifaVariable, double tarifaFija, double consumoMensualMinimo,
			double consumoMensualMaximo) {
		this.normalVariable = tarifaVariable;
		this.normalMensual = tarifaFija;
		this.consumoMensualMaximo = consumoMensualMaximo;
		this.consumoMensualMinimo = consumoMensualMinimo;
	}

	public double getNormalVariable() {
		return this.normalVariable;
	}

	public double estimarFacturacionCargoFijo() {
		return this.normalMensual;
	}

	public double estimarFacturacionCargoVariable(Cliente cliente) {
		return cliente.consumoTotalEstimadoPorHora() * normalVariable;
	}

	/*public Categoria categoriaCliente(double consumo) {
		for (Categoria cat : Categoria.values()) {
		}
		;
		return R1;
	}*/

	public boolean pertenece(double consumo) {
		return consumo < this.consumoMensualMaximo && consumo > this.consumoMensualMinimo;
	}

}
