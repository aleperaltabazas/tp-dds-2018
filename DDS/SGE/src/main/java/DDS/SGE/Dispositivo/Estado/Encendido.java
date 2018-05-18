package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.*;

public class Encendido extends ConEnergia {
	@Override
	public void encender(DispositivoInteligente dispositivo) { }
	
	public void ahorraEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroDeEnergia());
	}
}
