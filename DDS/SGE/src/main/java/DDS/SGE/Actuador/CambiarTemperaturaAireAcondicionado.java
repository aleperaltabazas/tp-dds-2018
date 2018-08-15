package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class CambiarTemperaturaAireAcondicionado implements Actuador{
	
	DispositivoInteligente dispositivo;
	
	public CambiarTemperaturaAireAcondicionado(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public void accionar(){
		dispositivo.getFabricante().actuar();
	}
	
	public DispositivoInteligente getDispositivo() {
		return this.dispositivo;
	}

}

