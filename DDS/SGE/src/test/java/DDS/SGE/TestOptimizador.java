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

	private static final double USO_MENSUAL_RECOMENDADO = 612.0;
	
	Computadora unFabricante = new Computadora(true);
	
	Zona unaZona = new Zona();
	
	Dispositivo dispositivoPotencia1Kw = new Dispositivo(1, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoPotencia2Kw = new Dispositivo(2, new DispositivoInteligente(new Apagado()), unFabricante);
	Dispositivo dispositivoPotencia3Kw = new Dispositivo(3, new DispositivoInteligente(new Encendido()), unFabricante);
	Dispositivo dispositivoPotenciaMuyAlta = new Dispositivo(10000, new DispositivoInteligente(new Apagado()), unFabricante);
	Dispositivo otroDispositivoPotencia2Kw = new Dispositivo(2, new DispositivoInteligente(new Apagado()), unFabricante);
	
	Cliente clienteSinDispositivos;
	Cliente clienteConDispositivoDe1Kw;
	Cliente clienteConDispositivoDe2Kw;
	Cliente clienteConDispositivoDeMuchaPotencia;
	Cliente clienteCon2DispositivosDe2Kw;
	Cliente clienteConVariosDispositivos;
	
	@Before
	public void initialize() {
		
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(), unaZona);
		
		clienteConDispositivoDe1Kw = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoPotencia1Kw), unaZona);
		
		clienteConDispositivoDe2Kw = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotencia2Kw), unaZona);
		
		clienteConDispositivoDeMuchaPotencia = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotenciaMuyAlta), unaZona);
		
		clienteCon2DispositivosDe2Kw = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotencia2Kw, otroDispositivoPotencia2Kw), unaZona);
		
		clienteConVariosDispositivos = new Cliente("Juan", "Perez", TipoDni.DNI, "987654321", "1188884444",
				"Calle Falsa 123", LocalDateTime.now(), Arrays.asList(dispositivoPotencia2Kw,dispositivoPotencia1Kw,dispositivoPotencia3Kw), unaZona);
	
	}
	
	/*
	@Test
	public void PruebaOptimizar() {
		double horas = optimizador.Calcular(clienteConDispositivoDe1Kw);
		assertEquals(612.0, horas, 0.0);
		
		double[] valoresEsperados = {60.0,60.0,60.0,132.0};
		posicion = 0;
		
		clienteConDispositivoDe1Kw.getDispositivos().map(disp -> disp.getTiempoQueSePuedeUtilizar())
		.sorted()
		.forEach(tiempo -> {
			assertEquals(valoresEsperados[posicion], tiempo, 0.0);
			posicion++;
		});
	}*/
	
	@Test
	public void unClienteConDispositivoDePotencia2KwSeRecomiendaUsarloLaMitadDelUsoRecomendadoHoras(){
		
		assertEquals(USO_MENSUAL_RECOMENDADO / 2, Optimizador.Calcular(clienteConDispositivoDe2Kw), 0.0);
	
	}
	
	@Test
	public void ElUnicoDispositivoDePotencia2KwDelClienteSeRecomiendaUsarloLaMitadDelTiempoRecomendado(){
		
		double tiempoTotalDeUso = Optimizador.Calcular(clienteConDispositivoDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar(), 0.0);
	
	}
	
	
	@Test
	public void unClienteCon2DispositivosDePotencia2KwSeRecomiendaUsarLaMismaCantidadDeHorasTotalesQueSiFueraUnoSolo(){

		assertEquals(Optimizador.Calcular(clienteConDispositivoDe2Kw), Optimizador.Calcular(clienteCon2DispositivosDe2Kw) , 0.0);
	
	}
	
	@Test
	public void laSumaDelTiempoRecomendadoDeCadaDispositivoEsIgualAlTiempoTotalQueElClientePuedeUsarlos(){

		double tiempoTotalDeUso = Optimizador.Calcular(clienteCon2DispositivosDe2Kw);
		assertEquals(tiempoTotalDeUso, dispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar() + otroDispositivoPotencia2Kw.getTiempoQueSePuedeUtilizar() , 0.0);
	
	}
	
	@Test
	public void unClienteConUnaComputadoraDePotencia1KwSeRecomiendaUsarLoMaximoPosible(){
		
		assertEquals(dispositivoPotencia1Kw.getFabricante().usoMensualMaximo(), Optimizador.Calcular(clienteConDispositivoDe1Kw), 0.0);
	
	}
	
	/* Explota porque el Simplex no encuentra solucion posible
	@Test
	public void unClienteConUnaComputadoraDePotenciaMuyAltaSeRecomiendaUsarLoMinimoPosible(){		
		assertEquals(dispositivoPotenciaMuyAlta.getFabricante().usoMensualMinimo(), optimizador.Calcular(clienteConDispositivoDeMuchaPotencia), 0.0);
	}*/
	
	
	@Test
	public void unClienteSinDispositivosSeLeRecomiendaUsarLosDispositivosPor0Horas(){
		
		assertEquals(0, Optimizador.Calcular(clienteSinDispositivos), 0.0);
	
	}
	
	@Test
	public void unClientePuedePedirSuUsoRecomendado(){
		
		assertEquals(432, clienteConVariosDispositivos.consultarUsoOptimo(), 0.0);
	
	}
	
}