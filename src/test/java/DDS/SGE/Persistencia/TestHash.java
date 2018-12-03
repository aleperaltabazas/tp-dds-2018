package DDS.SGE.Persistencia;

import static org.junit.Assert.*;
import DDS.SGE.Web.*;

import org.junit.Test;

public class TestHash {

	@Test
	public void testHashCadenaVacia() {
		assertTrue(HashProvider.hash("").equalsIgnoreCase("d41d8cd98f00b204e9800998ecf8427e"));
	}

	@Test
	public void testHashDeABCEsDistintoDeabc() {
		assertFalse(HashProvider.hash("abc").equalsIgnoreCase(HashProvider.hash("ABC")));
	}

}
