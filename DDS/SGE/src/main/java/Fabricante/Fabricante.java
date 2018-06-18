package Fabricante;

import java.time.LocalDateTime;

import DDS.SGE.Dispositivo.TipoDispositivo;
import DDS.SGE.Notificaciones.InteresadoEnAdaptaciones;
import DDS.SGE.Notificaciones.InteresadoEnNuevosDispositivos;

public interface Fabricante {
	public int Medir();
	public void Actuar();
}
