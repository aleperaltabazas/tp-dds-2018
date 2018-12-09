package DDS.SGE.Solicitud;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Dispositivo.MetodoDeCreacion;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestSolicitud {
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
    }

    @Test
    public void testAceptarUnaSolicitudHaceQueElClienteTengaUnDispositivoNuevo() {
        SolicitudCerrada aceptada = solicitud.aceptar(administrador);

        assertEquals(aceptada.getAdministrador(), administrador);
        assertEquals(aceptada.getEstado(), EstadoDeSolicitud.aceptada);
        assertTrue(cliente.getDispositivos().anyMatch(d -> d.getNombre() == dispositivo.getNombre()));
    }

    @Test
    public void testRechazarUnaSolicitudNoDeberiaHacerQueElClienteTenaUnDispositivoNuevo() {
        SolicitudCerrada rechazada = solicitud.rechazar(administrador);

        assertFalse(cliente.getDispositivos().anyMatch(d -> d.getNombre() == dispositivo.getNombre()));
    }
}
