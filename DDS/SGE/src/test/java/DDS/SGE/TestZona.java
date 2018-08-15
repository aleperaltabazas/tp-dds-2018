package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import Fabricante.Computadora;
import Fabricante.Heladera;
import Geoposicionamiento.Transformador;
import Geoposicionamiento.Zona;

public class TestZona {

	Computadora unFabricante = new Computadora(true);

	Zona zona_A = new Zona();
	Zona zona_B = new Zona();

	int diasDelMes = LocalDate.now().lengthOfMonth();
	Dispositivo unDispositivo = new Dispositivo(0.78, new DispositivoEstandar(24));
	Dispositivo otroDispositivo = new Dispositivo(0.09, new DispositivoInteligente(new Encendido(), unFabricante));

	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente elCliente = new Cliente("El", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente otroCliente = new Cliente("Otro", "Cliente", TipoDni.DNI, "111111112", "1123456780",
			"Otra Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));

	Transformador transformador_1 = new Transformador(zona_A);
	Transformador transformador_2 = new Transformador(zona_A);
	Transformador transformador_3 = new Transformador(zona_A);
	Transformador transformador_4 = new Transformador(zona_A);
	Transformador transformador_5 = new Transformador(zona_B);

		@Before
	public void initialize() {
		unCliente.recategorizar();
		otroCliente.recategorizar();
		zona_A.agregarTransformadores(Arrays.asList(transformador_1, transformador_2, transformador_3, transformador_4));
	}
	
	@Test
	public void El_Consumo_De_Zona_Es_Consumo_De_Clientes() {
		unCliente.conectarseAEsteTransformador(transformador_1);
		elCliente.conectarseAEsteTransformador(transformador_2);
		assertEquals(unCliente.consumoTotalEstimadoDiario() + otroCliente.consumoTotalEstimadoDiario(), zona_A.consumoTotal(), 0);
	}
}
