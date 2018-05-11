package DDS.SGE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;

public class TestObtenerTiempoComoAdministrador {

	LocalDate fechaDeAltaDeMarco = LocalDate.of(2017, 3, 1);
	Administrador unAdministrador = new Administrador("Marco", "Polo", "Rivadavia 1100", fechaDeAltaDeMarco, 42);

	@Test
	public void testUnAdministradorTieneMesesComoAdminIgualALaDiferenciaEntreLaFechaActualYLaFechaDeAlta() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaDeAlta = unAdministrador.getFechaAltaSistema();
		long diferenciaDeAnios = fechaActual.getYear() - fechaDeAlta.getYear();
		long diferenciaDeMeses = fechaActual.getMonthValue() - fechaDeAlta.getMonthValue();		
		long mesesTotalesComoAdministrador = diferenciaDeAnios * 12 + diferenciaDeMeses;		
		assertEquals(mesesTotalesComoAdministrador, unAdministrador.cantidadDeMesesComoAdmin());
	}
}
