package DDS.SGE;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import Fabricante.Computadora;
import Geoposicionamiento.Zona;

public class TestRecategorizar {
	
	Zona unaZona = new Zona();
	
	Computadora unFabricante = new Computadora(true);

	Dispositivo dispositivoConMedioConsumo = new Dispositivo(new DispositivoEstandar(24, 0.6));
	Dispositivo dispositivoConAltoConsumo = new Dispositivo(new DispositivoEstandar(24, 2));
	Dispositivo dispositivoConConsumoParaCategoriaR8 = new Dispositivo(new DispositivoEstandar(24, 1.2));

	Cliente clienteSinDispositivos = new Cliente("Sherlock", "Holmes", TipoDni.DNI, "123456789", "1144448888",
			"Baker St. 221b", LocalDateTime.now(), Arrays.asList());
	Cliente clienteDerrochador = new Cliente("Lucila", "Salmeron", TipoDni.DNI, "123454321", "1155667788",
			"Calle verdadera 321", LocalDateTime.now(), Arrays.asList(dispositivoConMedioConsumo, dispositivoConAltoConsumo));
	Cliente clienteDeCategoriaR8 = new Cliente("Jorge", "Rodriguez", TipoDni.DNI, "543212345", "1188776655",
			"Calle verdadera 654", LocalDateTime.now(), Arrays.asList(dispositivoConConsumoParaCategoriaR8));


	@Before
	public void initialize() {
		clienteSinDispositivos.recategorizar();
		clienteDerrochador.recategorizar();
		clienteDeCategoriaR8.recategorizar();
	}
	
	@Test
	public void testLaCategoriaDeUnClienteNuevoEsR1() {
		Cliente unClienteNuevo = new Cliente("", "", TipoDni.DNI, "", "", "", LocalDateTime.now(), Arrays.asList());
		assertEquals(Categoria.R1, unClienteNuevo.getCategoria());
	}
	
	@Test
	public void testLaCategoriaDeUnClienteSinDispositivosEsR1() {
		assertEquals(Categoria.R1, clienteSinDispositivos.getCategoria());
	}

	@Test
	public void testLaCategoriaDeLucilaEsR9TrasCategorizarla() {
		assertEquals(Categoria.R9, clienteDerrochador.getCategoria());
	}

	@Test
	public void testElclienteDeCategoriaR8AlRecategorizarseSuCategoriaEsR8() {
		assertEquals(Categoria.R8, clienteDeCategoriaR8.getCategoria());
	}

}
