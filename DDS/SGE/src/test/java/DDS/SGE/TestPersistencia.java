package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;

public class TestPersistencia {

	Cliente clienteSinDispositivos;
	
	@Before
	public void Inicializar() {
		EntityManagerHelper.beginTransaction();
		clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
				"Av siempre viva 742", LocalDateTime.now(), Arrays.asList());
	}
	
   @After
   public void after() {
	   EntityManagerHelper.rollback();
   }

   @Test
   public void PersistirUusuarioYLuegoCambiarleGeolocalizacion() {
	   
   }
}
