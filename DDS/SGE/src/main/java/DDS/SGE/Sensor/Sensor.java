package DDS.SGE.Sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.Dispositivo;

public interface Sensor {
	void Medir();
	void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos);
}
