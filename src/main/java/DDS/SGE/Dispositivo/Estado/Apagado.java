package DDS.SGE.Dispositivo.Estado;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import DDS.SGE.Dispositivo.*;

@Entity
public class Apagado extends EstadoDelDispositivo {

	@Override
	public void apagar(DispositivoInteligente dispositivo) {
	}
	
	@Override
	public void ahorraEnergia(DispositivoInteligente dispositivo) {
	
	}
	
	@Override
	public void encender(DispositivoInteligente dispositivo) {
		super.encender(dispositivo);
		dispositivo.getRepositorioTiempoEncendido().encender(LocalDateTime.now());
	}	

	@Override
	public boolean estaEncendido() {
		return false;
	}
	
	@Override
	public double getIntensidad() {
		return 0;
	}

	@Override
	public void setIntensidad(double nuevoValor) {

	}

	@Override
	public void setModo(ModoFrio_Calor nuevoModo) {

	}

	@Override
	public ModoFrio_Calor getModo() {
		return null;
	}

}
