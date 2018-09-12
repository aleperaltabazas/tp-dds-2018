package DDS.SGE;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Cliente.TipoDni;
import Geoposicionamiento.Transformador;
import junit.framework.Assert;

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
	   //TODO: Ahora se estaria cambiando el domicilio, la idea es cambiarle la geo
	   EntityManagerHelper.entityManager().persist(clienteSinDispositivos);
	   Cliente clientePersistido = EntityManagerHelper.entityManager().find(Cliente.class,
			   clienteSinDispositivos.getId());
	   clientePersistido.setDomicilio("calle x");
	   EntityManagerHelper.entityManager().persist(clientePersistido);
	   Cliente clienteActualizado = EntityManagerHelper.entityManager().find(Cliente.class,
			   clienteSinDispositivos.getId());
	   Assert.assertEquals("calle x", clienteActualizado.getDomicilio());
   }
}
