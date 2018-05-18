package DDS.SGE.Dispositivo.Estado;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class AhorroDeEnergia extends EstadoDelDispositivo {
	
	
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	}
}
