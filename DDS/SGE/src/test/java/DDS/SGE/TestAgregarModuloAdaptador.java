package DDS.SGE;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Fabricante;

public class TestAgregarModuloAdaptador {

	Fabricante unFabricante = new FabricanteTest(1);
	Encendido encendido = new Encendido();
	DispositivoInteligente unDispositivoInteligente = new DispositivoInteligente(encendido, unFabricante);

	Dispositivo dispositivoEstandar = new Dispositivo(new DispositivoEstandar(24, 0.78));
	Dispositivo dispositivoInteligenteQueNoEsDelCliente = new Dispositivo(unDispositivoInteligente);
	Dispositivo dispositivoInteligenteQueSiEsDelCliente = new Dispositivo(unDispositivoInteligente);
	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789", "Una Calle",
			LocalDateTime.now(), Arrays.asList(dispositivoEstandar, dispositivoInteligenteQueSiEsDelCliente));

	@Test
	public void testAdaptarUnEstandarLoVuelveInteligente() {
		unCliente.agregarModuloAdaptadorA(dispositivoEstandar);
		assertEquals(DispositivoInteligente.class, dispositivoEstandar.getTipoDispositivo().getClass());
	}

	@Test
	public void testSiElClienteNoTieneElDispositivoNoLoAdapta() {
		unCliente.agregarModuloAdaptadorA(dispositivoInteligenteQueNoEsDelCliente);
		assertEquals(DispositivoInteligente.class,
				dispositivoInteligenteQueNoEsDelCliente.getTipoDispositivo().getClass());
	}

	@Test
	public void testAdaptarUnInteligenteNoLoVuelveEstandar() {
		unCliente.agregarModuloAdaptadorA(dispositivoInteligenteQueSiEsDelCliente);
		assertEquals(DispositivoInteligente.class,
				dispositivoInteligenteQueSiEsDelCliente.getTipoDispositivo().getClass());
	}

}
