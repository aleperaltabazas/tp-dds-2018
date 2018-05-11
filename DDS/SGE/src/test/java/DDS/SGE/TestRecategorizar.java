package DDS.SGE;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;

public class TestRecategorizar {

	Dispositivo dispositivoConMedioConsumo = new Dispositivo(0.6, false);
	Dispositivo dispositivoConAltoConsumo = new Dispositivo(2, true);
	Dispositivo dispositivoConConsumoParaCategoriaR8 = new Dispositivo(1.2, false);

	Cliente clienteSinDispositivos = new Cliente("Sherlock", "Holmes", TipoDni.DNI, "123456789", "1144448888",
			"Baker St. 221b", LocalDate.now(), Arrays.asList());
	Cliente clienteDerrochador = new Cliente("Lucila", "Salmeron", TipoDni.DNI, "123454321", "1155667788",
			"Calle verdadera 321", LocalDate.now(), Arrays.asList(dispositivoConMedioConsumo, dispositivoConAltoConsumo));
	Cliente clienteDeCategoriaR8 = new Cliente("Jorge", "Rodriguez", TipoDni.DNI, "543212345", "1188776655",
			"Calle verdadera 654", LocalDate.now(), Arrays.asList(dispositivoConConsumoParaCategoriaR8));

	@Before
	public void initialize() {
		// clienteSinDispositivos.recategorizar();
		clienteDerrochador.recategorizar();
		clienteDeCategoriaR8.recategorizar();
	}
	
	@Test
	public void testLaCategoriaDeUnClienteNuevoEsR1() {
		Cliente unClienteNuevo = new Cliente("", "", TipoDni.DNI, "", "", "", LocalDate.now(), Arrays.asList());
		assertEquals(Categoria.R1, unClienteNuevo.getCategoria());
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
