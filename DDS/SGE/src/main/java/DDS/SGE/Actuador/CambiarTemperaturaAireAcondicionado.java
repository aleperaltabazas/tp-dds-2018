package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class CambiarTemperaturaAireAcondicionado extends Actuador{
	
	DispositivoInteligente dispositivo;
	
	public CambiarTemperaturaAireAcondicionado(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	@Override
	public void accionar(){
		dispositivo.getFabricante().actuar();
	}
	
	@Override
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}

