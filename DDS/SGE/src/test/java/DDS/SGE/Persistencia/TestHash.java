package DDS.SGE.Persistencia;

import static org.junit.Assert.*;
import DDS.SGE.Web.*;

import org.junit.Test;

public class TestHash {

	@Test
	public void testHashCadenaVacia() {
		assertEquals(HashProvider.hash("").toLowerCase(), "d41d8cd98f00b204e9800998ecf8427e".toLowerCase());
	}
	
	@Test
	public void testHashDeABCEsDistintoDeabc() {
		assertNotEquals(HashProvider.hash("abc").toLowerCase(), HashProvider.hash("ABC").toLowerCase());
	}

}
