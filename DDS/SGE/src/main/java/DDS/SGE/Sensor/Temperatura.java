package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.Dispositivo;

public class Temperatura implements Sensor {

	Dispositivo dispositivo;
	
	public Temperatura(Dispositivo dispositivo){
		this.dispositivo = dispositivo;
	}	
	
	// Podria cambiar el estado del dispositivo segun la temperatura obtenida
	public double Medir() {

	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		// TODO Auto-generated method stub

	}

}
