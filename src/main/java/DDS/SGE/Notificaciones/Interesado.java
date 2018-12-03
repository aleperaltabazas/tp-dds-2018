package DDS.SGE.Notificaciones;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

public abstract class Interesado {

	public abstract void sucedio(Cliente unCliente,Dispositivo dispositivo);
	
}
