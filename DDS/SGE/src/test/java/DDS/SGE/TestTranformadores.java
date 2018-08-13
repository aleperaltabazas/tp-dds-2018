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

public class TestTranformadores {

	Computadora unFabricante = new Computadora(true);
	Heladera otroFarbicante = new Heladera(true);
	
	Zona zona_A = new Zona();
	Zona zona_B = new Zona();
	
	int diasDelMes = LocalDate.now().lengthOfMonth();
	Dispositivo unDispositivo = new Dispositivo(0.78, new DispositivoEstandar(24), unFabricante);
	Dispositivo otroDispositivo = new Dispositivo(0.09, new DispositivoInteligente(new Encendido()), new Heladera(true));
	
	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente otroCliente = new Cliente("El", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente elCliente = new Cliente("Otro", "Cliente", TipoDni.DNI, "111111112", "1123456780",
			"Otra Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	
	Transformador transformador_1 = new Transformador(zona_A);
	Transformador transformador_2 = new Transformador(zona_B);
	Transformador transformador_3 = new Transformador(zona_A);
	
	@Before
	public void initialize() {
		unCliente.recategorizar();
		otroCliente.recategorizar();
	}
	
	@Test
	public void Transformador_Suministra_Suma_De_Consumo_De_Usuarios_Conectados() {
		zona_A.agregarTransformadores(Arrays.asList(transformador_1, transformador_3));
		unCliente.conectarseAEsteTransformador(transformador_1);
		otroCliente.conectarseAEsteTransformador(transformador_3);
		assertEquals(unCliente.consumoTotalEstimadoDiario() + otroCliente.consumoTotalEstimadoDiario(), transformador_1.suministra() + transformador_3.suministra(), 0);
	}
	
	@Test (expected = RuntimeException.class)
	public void Cliente_No_Puede_Conectarse_A_Transformador_De_Otra_Zona() {
		zona_B.agregarTransformadores(Arrays.asList(transformador_2));
		unCliente.conectarseAEsteTransformador(transformador_2);
		
		
	}

}
