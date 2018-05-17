package DDS.SGE;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;

public class TestCalcularConsumosEnDispositivosInteligentes {
	
	//RepositorioDeTiempoEncendido repositorio = new RepositorioDeTiempoEncendido()
	Dispositivo dispositivoEncendido = new Dispositivo(1, new DispositivoInteligente(new Encendido()));
	Dispositivo dispositivoApagado = new Dispositivo(1, new DispositivoInteligente(new Apagado()));
	Cliente clienteConTodoApagado = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
			"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoApagado, dispositivoApagado));
	
	@Before
	public void initialize() {
		dispositivoEncendido.apagar();
		//Deberia tener los intervalos definidos, sino tengo que simular el paso del tiempo
	}

}
