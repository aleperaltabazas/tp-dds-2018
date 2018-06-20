package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public interface Sensor {
	double medir();
	boolean hayQueActuar();
	public DispositivoInteligente getDispositivo();
}
