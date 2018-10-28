package DDS.SGE.Sensor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import DDS.SGE.Dispositivo.Dispositivo;

@Entity
public class Consumo extends Sensor {

	@OneToOne
	Dispositivo dispositivo;
	static int HORAS_DEL_MES = 24 * 30;
	
	protected Consumo() {}
	
	public Consumo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	@Override
	public void actualizarMediciones() {
		
	}

	@Override
	public double medir() {
		return 0;
	}

	@Override
	public boolean hayQueActuar() {
		return dispositivo.consumoTotalHaceNHoras(HORAS_DEL_MES) > dispositivo.getTiempoQueSePuedeUtilizar();
	}

}
