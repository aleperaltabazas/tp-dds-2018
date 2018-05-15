package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public class Encendido extends ConEnergia {
	public void encender(DispositivoInteligente dispositivo) { }
	
	public void ahorraEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia());
	}
}
