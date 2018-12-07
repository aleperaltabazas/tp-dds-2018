package DDS.SGE.Dispositivos;

import DDS.SGE.Dispositivo.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestDispositivoDeCatalogo {
    @Test
    public void testConstruirUnDispositivoDeCatalogoMeDeberiaGenerarUnDispositivo() {
        DispositivoDeCatalogo dispositivoDeCatalogo = new DispositivoDeCatalogo("Un dispositivo", 100, false, true, MetodoDeCreacion.AIRE);

        Dispositivo dispositivoConstruido1 = dispositivoDeCatalogo.construir();
        Dispositivo dispositivoConstruido2 = dispositivoDeCatalogo.construir();

        assertNotEquals(dispositivoConstruido1, dispositivoConstruido2);
        assertEquals(dispositivoConstruido1.getNombre(), "Un dispositivo");
    }
}
