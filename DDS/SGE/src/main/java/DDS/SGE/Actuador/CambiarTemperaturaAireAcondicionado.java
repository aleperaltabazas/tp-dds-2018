package DDS.SGE.Actuador;

import DDS.SGE.Dispositivo.DispositivoInteligente;

public class CambiarTemperaturaAireAcondicionado implements Actuador{
		
	public void accionarSobre(DispositivoInteligente dispositivo){
		dispositivo.getFabricante().actuar();
	}

}

