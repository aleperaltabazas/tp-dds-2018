package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.DispositivoInteligente;
import Fabricante.AireAcondicionado;

public class Temperatura extends Sensor {

	AireAcondicionado fabricante;

	double temperaturaAmbiente = 22;
	double temperaturaActual;

	public Temperatura(double temperaturaAmbiente, DispositivoInteligente unDispositivo) {
		this.temperaturaAmbiente = temperaturaAmbiente;
		this.fabricante = (AireAcondicionado) unDispositivo.getFabricante();
	}

	public double getTemperaturaAmbiente() {
		return temperaturaAmbiente;
	}

	public void setTemperaturaAmbiente(double temperaturaAmbiente) {
		this.temperaturaAmbiente = temperaturaAmbiente;
	}

	@Override
	public void actualizarMediciones() {
		// De alguna manera sensa el ambiente y hace un setTemperatura() con la medición
	}

	@Override
	public double medir() {
		return temperaturaAmbiente;
	}

	@Override
	public boolean hayQueActuar() {
		return fabricante.hayQueActuar(this.temperaturaAmbiente);
	}

}
