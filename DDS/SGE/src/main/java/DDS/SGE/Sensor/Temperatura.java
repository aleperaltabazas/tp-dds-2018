package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Temperatura implements Sensor {

	DispositivoInteligente dispositivo;
	
	public Temperatura(DispositivoInteligente dispositivo){
		this.dispositivo = dispositivo;
	}	
	
	// Podria cambiar el estado del dispositivo segun la temperatura obtenida
	public void Medir() {

	}

	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		// TODO Auto-generated method stub

	}

}
