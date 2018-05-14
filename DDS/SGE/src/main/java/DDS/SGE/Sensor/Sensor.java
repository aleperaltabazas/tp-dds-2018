package DDS.SGE.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import DDS.SGE.Dispositivo;

public interface Sensor {
	double Medir();
	void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos);
}
