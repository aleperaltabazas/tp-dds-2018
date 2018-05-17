package DDS.SGE.Notificaciones;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.TipoDispositivo;

public class InteresadoEnNuevosDispositivos implements Interesado{

	Cliente cliente;
	@Override
	public void sucedio(Cliente unCliente,Dispositivo dispositivo) {
		this.cliente = unCliente;
		dispositivo.seAgregoNuevoDispositivo(this); 				
	}
	
	public void sumarPuntos() {
		cliente.sumarPuntos(15);
	}	
}
