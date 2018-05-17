package DDS.SGE.Notificaciones;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

public class InteresadoEnAdaptaciones implements Interesado{
	
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
