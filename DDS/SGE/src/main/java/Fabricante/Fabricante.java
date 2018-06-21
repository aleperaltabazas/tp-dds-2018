package Fabricante;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.TipoDispositivo;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

public interface Fabricante {
	public int medir();
	public void actuar();
	
	public boolean hayQueActuar(double temperatura);
}
