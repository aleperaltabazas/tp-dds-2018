package DDS.SGE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import DDS.SGE.Cliente.TipoDni;

public class TestCalcularConsumoEstimado {

	int diasDelMes = LocalDate.now().lengthOfMonth();
	Dispositivo unDispositivo = new Dispositivo(0.78, true);
	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDate.now(), Arrays.asList(unDispositivo));	
	
	@Before
	public void initialize() {
		unCliente.recategorizar();
	}
	
	@Test
	public void testElConsumoMensualDeUnClienteEsElCorrespondienteAlConsumoDelDispositivoConvertidoALaDuracionDelMesActual() {
		assertEquals(unDispositivo.getConsumoKWPorHora() * 24 * diasDelMes,
				unCliente.consumoTotalPorMes(), 0);
	}
	
	@Test
	public void testUnClienteTiene3DispositivosIgualesYSuConsumoTotalPorHoraEsElTripleDelConsumoDelDispositivo() {
		List<Dispositivo> tresDispositivosIguales = Arrays.asList(unDispositivo, unDispositivo, unDispositivo);
		unCliente.setDispositivos(tresDispositivosIguales);

		assertEquals(unDispositivo.getConsumoKWPorHora() * 3, unCliente.consumoTotalEstimadoPorHora(), 0);
	}
	
	@Test
	public void testEnAbrilElConsumoTotalPorMesDeUnClienteEsMayorQueEnFebrero() {
		LocalDate abril = LocalDate.of(2018, 4, 22);
		LocalDate febrero = LocalDate.of(2018, 2, 27);

		assertTrue(unCliente.consumoTotalDeUnMesEspecifico(abril) > unCliente.consumoTotalDeUnMesEspecifico(febrero));
	}
	
	@Test
	public void testLaFacturacionEstimadaVariableDelClienteEsCorrespondienteASuCategoria() {
		Categoria categoriaDelCliente = unCliente.getCategoria();
		
		assertEquals(unCliente.consumoTotalEstimadoPorHora() * categoriaDelCliente.getNormalVariable(),
				categoriaDelCliente.estimarFacturacionCargoVariable(unCliente), 0);
	}
}
