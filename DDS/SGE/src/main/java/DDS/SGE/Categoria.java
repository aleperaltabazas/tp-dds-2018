package DDS.SGE;

public enum Categoria {
	R1(0.644, 18.76), R2(0.644, 35.32), R3(0.681, 60.71), R4(0.738, 71.74), R5(0.794, 110.38), R6(0.832,
			220.75), R7(0.851, 443.59), R8(0.851, 545.96), R9(0.851, 887.19);

	private double normalVariable;
	private double normalMensual;

	private Categoria(double tarifaVariable, double tarifaFija) {
		this.normalVariable = tarifaVariable;
		this.normalMensual = tarifaFija;
	}

	public double estimarFacturacionCargoFijo() {
		return this.normalMensual;
	}

	public double estimarFacturacionCargoVariable(Cliente cliente) {
		return cliente.consumoTotalPorHora() * normalVariable;
	}
}
