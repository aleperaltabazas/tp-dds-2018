package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.Dispositivo;

public class Temperatura implements Sensor {

	Dispositivo dispositivo;
	
	public Temperatura(Dispositivo dispositivo){
		this.dispositivo = dispositivo;
	}	
	
	public double Medir() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		// TODO Auto-generated method stub

	}

}
