package DDS.SGE.Dispositivo;

import DDS.SGE.Cliente;
import java.time.LocalDateTime;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;

public class DispositivoEstandar implements TipoDispositivo {
	long usoEstimadoDiario;

	public DispositivoEstandar(long usoEstimadoDiario) {
		this.usoEstimadoDiario = usoEstimadoDiario;
	}

	public boolean estaEncendido() {
		return false;
	}

	public long usoEstimadoDiario() {
		return usoEstimadoDiario;
	}

	public TipoDispositivo adaptar() {
		return new DispositivoInteligente(new Apagado()); // No se a que estado lo convierte.
	}

	public void encender() {
		// No hace nada.
	}

	public void apagar() {
		// No hace nada.
	}

	public double tiempoTotalEncendidoHaceNHoras(int horas) {
		// Nos esta trayendo complicaciones el strategy, sera que lo estamos pensando
		// mal? Diria de tirar excepcion pero imaginense hacer eso para cada cosa que el
		// dispositivo estandar no sea capaz de hacer
		return 0;
	}


	public void agregado(Cliente unCliente) {
		// No hace nada.
		
	}	

	
	public double tiempoTotalEncendidoEnUnPeriodo(LocalDateTime principioPeriodo, LocalDateTime finPeriodo) {
		return 0;
	}
}