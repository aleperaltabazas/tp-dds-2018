package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;
import Geoposicionamiento.Zona;

public class TestOptimizador {

	int posicion;
	Optimizador optimizador = new Optimizador();
	
	Computadora unFabricante = new Computadora(true);
	Zona unaZona = new Zona();
	Dispositivo dispositivoEncendido = new Dispositivo(1, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoApagado = new Dispositivo(1, new DispositivoInteligente(new Apagado()), unFabricante);
	Cliente clienteSinDispositivos;
	Cliente clienteConVariosDispostivos;
	Cliente clienteConTodoApagado;
	
	@Before
	public void initialize() {
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(), unaZona);
		clienteConVariosDispostivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoEncendido, dispositivoApagado, dispositivoEncendido, dispositivoEncendido), unaZona);
		clienteConTodoApagado = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoApagado, dispositivoApagado), unaZona);
	}
	
	@Test
	public void PruebaOptimizar() {
		double horas = optimizador.Calcular(clienteConVariosDispostivos);
		assertEquals(613.0, horas, 0.0);
		
		double[] valoresEsperados = {60.0,60.0,60.0,132.0};
		posicion = 0;
		
		clienteConVariosDispostivos.getDispositivos().map(disp -> disp.getTiempoQueSePuedeUtilizar())
		.sorted()
		.forEach(tiempo -> {
			assertEquals(valoresEsperados[posicion], tiempo, 0.0);
			posicion++;
		});
	}

}
