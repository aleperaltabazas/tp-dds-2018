package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Actuador.CambiarACalor;
import DDS.SGE.Actuador.CambiarAFrio;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Temperatura implements Sensor {

	CambiarACalor actuador_calor;
	CambiarAFrio actuador_frio;
	
	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos) {
		// TODO Auto-generated method stub

	}

	@Override
	public double medir(DispositivoInteligente unDispositivo) {
		return unDispositivo.getTemperaturaAmbiente();
	}

	@Override
	public void controlar(DispositivoInteligente unDispositivo) {
		if(unDispositivo.getTemperaturaAmbiente() > 24) {
			actuador_frio.accionarSobre(unDispositivo);
		}
		else {
			if(unDispositivo.getTemperaturaAmbiente() < 15) {
				actuador_calor.accionarSobre(unDispositivo);
			}
		}
		
	}

}
