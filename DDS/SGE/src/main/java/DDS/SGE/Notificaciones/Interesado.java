package DDS.SGE.Notificaciones;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

public interface Interesado {

	void sucedio(Cliente unCliente,Dispositivo dispositivo);

}
