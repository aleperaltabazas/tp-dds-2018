package DDS.SGE.Dispositivo.Estado;

import javax.persistence.Entity;

import DDS.SGE.Dispositivo.DispositivoInteligente;

@Entity
public class AhorroDeEnergia extends EstadoDelDispositivo {
	
	
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	}
}
