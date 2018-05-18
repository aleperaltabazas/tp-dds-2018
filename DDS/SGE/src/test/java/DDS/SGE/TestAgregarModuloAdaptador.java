package DDS.SGE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Dispositivo.Estado.Encendido;

public class TestAgregarModuloAdaptador {
	
	Dispositivo dispositivoEstandar = new Dispositivo(0.78, new DispositivoEstandar(24));
	Dispositivo dispositivoInteligenteQueNoEsDelCliente = new Dispositivo(0.50, new DispositivoInteligente(new Encendido()));
	Dispositivo dispositivoInteligenteQueSiEsDelCliente = new Dispositivo(0.50, new DispositivoInteligente(new Encendido()));
	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(dispositivoEstandar,dispositivoInteligenteQueSiEsDelCliente));	
	
	
	@Test
	public void testAdaptarUnEstandarLoVuelveInteligente() {
		unCliente.agregarModuloAdaptadorA(dispositivoEstandar);
		assertEquals(DispositivoInteligente.class, dispositivoEstandar.getTipoDispositivo().getClass());
	}
	
	@Test
	public void testSiElClienteNoTieneElDispositivoNoLoAdapta() {
		unCliente.agregarModuloAdaptadorA(dispositivoInteligenteQueNoEsDelCliente);
		assertEquals(DispositivoInteligente.class, dispositivoInteligenteQueNoEsDelCliente.getTipoDispositivo().getClass());
	}
	
	
	//Este test me genera dudas, ya que no se que hace funcionalmente nuestro sistema si adaptas un Inteligente
	@Test
	public void testAdaptarUnInteligenteLoVuelveEstandar() {
		unCliente.agregarModuloAdaptadorA(dispositivoInteligenteQueSiEsDelCliente);
		assertEquals(DispositivoEstandar.class, dispositivoInteligenteQueSiEsDelCliente.getTipoDispositivo().getClass());
	}
		
	
}
