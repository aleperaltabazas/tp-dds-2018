package DDS.SGE.Solicitud;

import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioSolicitudes;
import DDS.SGE.Usuarie.Administrador;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class ChabonQueTePersisteCuandoSeCierraUnaSolicitud implements WithGlobalEntityManager, TransactionalOps {
    public void aceptar(SolicitudAbierta solicitudAbierta, Administrador administrador) {
        solicitudAbierta.getCliente().agregarDispositivo(solicitudAbierta.getDispositivo().construir());

        withTransaction(() -> {
            this.cerrar(solicitudAbierta, administrador, Resolucion.aceptada);
            RepositorioClientes.getInstance().actualizarCliente(solicitudAbierta.getCliente());
        });
    }

    private void cerrar(SolicitudAbierta solicitudAbierta, Administrador administrador, Resolucion resolucion) {
        SolicitudCerrada solicitudCerrada = new SolicitudCerrada(solicitudAbierta.getCliente(), administrador, solicitudAbierta.getFechaCreacion(), solicitudAbierta.getDispositivo(), resolucion, solicitudAbierta.getId());
        solicitudAbierta.getCliente().setTieneNotificaciones(true);

        RepositorioClientes.getInstance().actualizarCliente(solicitudAbierta.getCliente());
        RepositorioSolicitudes.getInstance().saveOrUpdate(solicitudCerrada);
        RepositorioSolicitudes.getInstance().delete(solicitudAbierta);
    }

    public void rechazar(SolicitudAbierta solicitudAbierta, Administrador administrador) {
        withTransaction(() -> this.cerrar(solicitudAbierta, administrador, Resolucion.rechazada));
    }
}
