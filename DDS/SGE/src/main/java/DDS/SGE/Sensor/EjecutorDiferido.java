package DDS.SGE.Sensor;

import java.util.TimerTask;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class EjecutorDiferido extends TimerTask {

	Sensor sensorAEjecutar;
	DispositivoInteligente dispositivo;
	
	EjecutorDiferido(Sensor unSensor, DispositivoInteligente unDispositivo){
		this.sensorAEjecutar = unSensor;
		this.dispositivo = unDispositivo;
	}
	
	@Override
	public void run() {
		sensorAEjecutar.controlar(dispositivo);
	}
}
