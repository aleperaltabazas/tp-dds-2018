package DDS.SGE.Notificaciones;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

public class InteresadoEnNuevosDispositivos extends Interesado {

	Cliente cliente;

	@Override
	public void sucedio(Cliente unCliente, Dispositivo dispositivo) {
		this.cliente = unCliente;
		dispositivo.seAgregoNuevoDispositivo(this);
	}

	public void sumarPuntos() {
		cliente.sumarPuntos(15);
	}
}
