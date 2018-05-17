package DDS.SGE.Sensor;

import java.time.LocalDateTime;

import DDS.SGE.Actuador.CambiarACalor;
import DDS.SGE.Actuador.CambiarAFrio;
import DDS.SGE.Dispositivo.DispositivoInteligente;

public class Temperatura implements Sensor {

	CambiarACalor actuador_calor = new CambiarACalor();
	CambiarAFrio actuador_frio = new CambiarAFrio();
	double temperaturaAmbiente = 22;
	       // Esto queda por default. 
			// Despues vemos si hay función específica dada por la cátedra.
	
	public void ConfigurarTiempoDeEjecucion(LocalDateTime horaInicial, int intervaloDeMinutos, DispositivoInteligente unDispositivo) {
		// TODO Auto-generated method stub

	}

	@Override
	public double medir() {
		return temperaturaAmbiente;
	}

	@Override
	public void controlar(DispositivoInteligente unDispositivo) {
		if(this.temperaturaAmbiente > 27) {
			actuador_frio.accionarSobre(unDispositivo);
			unDispositivo.setTemperatura(24); // Valor arbitrario
		}
		else {
			if(this.temperaturaAmbiente < 15) {
				actuador_calor.accionarSobre(unDispositivo);
				unDispositivo.setTemperatura(22); // Valor arbitrario
			}
		}
		
	}

}
