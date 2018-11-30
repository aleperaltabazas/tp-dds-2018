package DDS.SGE.Persistencia;

import DDS.SGE.*;
import DDS.SGE.Dispositivo.Dispositivo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;

public class TestCliente {
	Cliente cliente = new Cliente("Un nombre", "Un apellido", Cliente.TipoDni.DNI, "1111", "4444", "Un domicilio", LocalDateTime.now(), new ArrayList<Dispositivo>());

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
