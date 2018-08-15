package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public interface Sensor {
	public void actualizarMediciones();
	double medir();
	boolean hayQueActuar(DispositivoInteligente dispositivo);
}
