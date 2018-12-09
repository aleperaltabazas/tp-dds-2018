package DDS.SGE.Persistencia;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Dispositivo.MetodoDeCreacion;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioSolicitudes;
import DDS.SGE.Solicitud.SolicitudAbierta;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSolicitud implements TransactionalOps, WithGlobalEntityManager {
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
    public void testPersistirUnaSolicitudYTraerla() {
        try {
            withTransaction(() -> {
                RepositorioClientes.getInstance().agregarCliente(cliente);
                RepositorioSolicitudes.getInstance().saveOrUpdate(solicitud);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<SolicitudAbierta> solicitudesDeGaston = RepositorioSolicitudes.getInstance().solicitudesAbiertasDe(cliente.getId());

        assertEquals(1, solicitudesDeGaston.size());
        assertEquals(solicitud, solicitudesDeGaston.get(0));
    }
}
