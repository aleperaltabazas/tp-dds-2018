package DDS.SGE.Notificaciones;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

public class InteresadoEnAdaptaciones extends Interesado{
	
	Cliente cliente;
	@Override
	public void sucedio(Cliente unCliente, Dispositivo dispositivo) {
		this.cliente = unCliente;
		dispositivo.seAdaptoUnDispositivo(this); 
	}
	public void sumarPuntos() {
		cliente.sumarPuntos(15);
	}

}
