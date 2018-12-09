package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Dispositivo.MetodoDeCreacion;
import DDS.SGE.Repositorios.RepositorioSolicitudes;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestSolicitud implements WithGlobalEntityManager, TransactionalOps {
    Cliente cliente;
    Administrador administrador;
    SolicitudAbierta solicitud;
    DispositivoDeCatalogo dispositivo;

    @Before
    public void initialize() {
        cliente = new Cliente("Gastón", "Prieto", Cliente.TipoDni.DNI, "Calle", "20", "42", LocalDateTime.now(), new ArrayList<Dispositivo>());
        administrador = new Administrador("Franco", "Bulgarelli", "Otra calle", LocalDateTime.now());
        dispositivo = new DispositivoDeCatalogo("Dispositivo", 20, false, false, MetodoDeCreacion.AIRE);
        solicitud = new SolicitudAbierta(cliente, dispositivo);

        withTransaction(() -> RepositorioSolicitudes.getInstance().saveOrUpdate(solicitud));
    }

    @Test
    public void testAceptarUnaSolicitudHaceQueElClienteTengaUnDispositivoNuevo() {
        solicitud.aceptar(administrador);

        assertTrue(cliente.getDispositivos().anyMatch(d -> d.getNombre() == dispositivo.getNombre()));
    }

    @Test
    public void testRechazarUnaSolicitudNoDeberiaHacerQueElClienteTenaUnDispositivoNuevo() {
        solicitud.rechazar(administrador);
        assertFalse(cliente.getDispositivos().anyMatch(d -> d.getNombre() == dispositivo.getNombre()));
    }
}
