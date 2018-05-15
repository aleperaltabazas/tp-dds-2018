package DDS.SGE.Dispositivo.Estado;

public class Apagado implements EstadoDelDispositivo {

	public boolean estaEncendido() {
		return false;
	}
	
	public double getIntensidad() {
		return 0;
	}

	
	// CON ESTOS DOS DEBERIA TIRAR UNA EXCEPCION //
	
	@Override
	public void setIntensidad(double nuevoValor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModo(ModoFrio_Calor nuevoModo) {
		// TODO Auto-generated method stub
		
	}
	
	

}
