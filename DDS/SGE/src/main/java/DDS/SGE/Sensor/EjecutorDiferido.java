package DDS.SGE.Sensor;

import java.util.TimerTask;

public class EjecutorDiferido extends TimerTask {

	Sensor sensorAEjecutar;
	
	EjecutorDiferido(Sensor unSensor){
		this.sensorAEjecutar = unSensor;
	}
	
	@Override
	public void run() {
		sensorAEjecutar.Medir();
	}

}
