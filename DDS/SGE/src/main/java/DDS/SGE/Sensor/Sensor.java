package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public interface Sensor {
	double medir(DispositivoInteligente unDispositivo);
	double medir();
	void controlar(DispositivoInteligente unDispositivo);
	void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos, DispositivoInteligente unDispositivo);
}
