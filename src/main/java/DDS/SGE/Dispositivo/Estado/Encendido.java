package DDS.SGE.Dispositivo.Estado;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import DDS.SGE.Dispositivo.*;

@Entity
public class Encendido extends EstadoDelDispositivo {
	@Override
	public void encender(DispositivoInteligente dispositivo) { }
	
	@Override
	public void apagar(DispositivoInteligente dispositivo) { 
		super.apagar(dispositivo);
		dispositivo.getRepositorioTiempoEncendido().apagar(LocalDateTime.now());
	}

	
}
