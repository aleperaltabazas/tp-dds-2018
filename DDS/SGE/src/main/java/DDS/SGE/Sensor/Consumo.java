package DDS.SGE.Sensor;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Consumo implements Sensor {

	Dispositivo dispositivo;
	static int HORAS_DEL_MES = 24 * 30;
	
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
