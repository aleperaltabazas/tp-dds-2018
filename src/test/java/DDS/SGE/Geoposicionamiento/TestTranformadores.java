package DDS.SGE.Geoposicionamiento;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import DDS.SGE.Usuarie.Cliente;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Usuarie.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Fabricante.Computadora;
import DDS.SGE.Fabricante.Heladera;
import DDS.SGE.Geoposicionamiento.Transformador;
import DDS.SGE.Geoposicionamiento.Zona;

public class TestTranformadores {

	Computadora unFabricante = new Computadora(true);
	Heladera otroFarbicante = new Heladera(true);
	
	Zona zona_A = new Zona();
	Zona zona_B = new Zona();
	
	int diasDelMes = LocalDate.now().lengthOfMonth();
	Dispositivo unDispositivo = new Dispositivo(new DispositivoEstandar(24, 1));
	Dispositivo otroDispositivo = new Dispositivo(new DispositivoInteligente(new Encendido(), otroFarbicante));
	
	Cliente unCliente = new Cliente("Un", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente otroCliente = new Cliente("El", "Cliente", TipoDni.DNI, "111111111", "1123456789",
			"Una Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	Cliente elCliente = new Cliente("Otro", "Cliente", TipoDni.DNI, "111111112", "1123456780",
			"Otra Calle", LocalDateTime.now(), Arrays.asList(unDispositivo));
	
	/*Transformador transformador_1 = new Transformador(zona_A);
	Transformador transformador_2 = new Transformador(zona_B);
	Transformador transformador_3 = new Transformador(zona_A);*/
	
	Transformador transformador_1 = new Transformador(1);
	Transformador transformador_2 = new Transformador(2);
	Transformador transformador_3 = new Transformador(3);
	
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
	
	/*@Test (expected = RuntimeException.class)
	public void Cliente_No_Puede_Conectarse_A_Transformador_De_Otra_Zona() {
		zona_B.agregarTransformadores(Arrays.asList(transformador_2));
		unCliente.conectarseAEsteTransformador(transformador_2);		
	}*/

}
