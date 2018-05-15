package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class AhorroDeEnergia extends ConEnergia {
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	}
}
