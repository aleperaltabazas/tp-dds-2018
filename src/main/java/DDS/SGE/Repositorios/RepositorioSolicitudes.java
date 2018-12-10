package DDS.SGE.Repositorios;

import DDS.SGE.Solicitud.Solicitud;
import DDS.SGE.Solicitud.SolicitudAbierta;
import DDS.SGE.Solicitud.SolicitudCerrada;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSolicitudes extends Repositorio {
    private static final RepositorioSolicitudes instance = new RepositorioSolicitudes();

    private RepositorioSolicitudes() {
    }

    public List<SolicitudAbierta> solicitudesAbiertasDe(Long id) {
        return entityManager().createQuery("from SolicitudAbierta s where s.cliente = " + id.toString()).getResultList();
    }

    public List<SolicitudCerrada> solicitudesCerradasDe(Long id) {
        return entityManager().createQuery("from SolicitudCerrada s where s.cliente = " + id.toString()).getResultList();
    }

    public List<SolicitudAbierta> listaAbiertas() {
        return entityManager().createQuery("from SolicitudAbierta ", SolicitudAbierta.class).getResultList();
    }

    public List<SolicitudCerrada> listarCerradasPor(Long id) {
        return entityManager().createQuery("from SolicitudCerrada s where s.administrador = " + id.toString()).getResultList();
    }

    public void saveOrUpdate(SolicitudCerrada solicitud) {
        this.persistir(solicitud);
    }

    public void saveOrUpdate(SolicitudAbierta solictud) {
        this.persistir(solictud);
    }

    public static RepositorioSolicitudes getInstance() {
        return instance;
    }

    public SolicitudAbierta findByIDAbierta(Long id) {
        return this.findByID(SolicitudAbierta.class, id);
    }

    public SolicitudCerrada findByIDCerrada(Long id) {
        return this.findByID(SolicitudCerrada.class, id);
    }

    public boolean algunaSolicitudSinLeerDeCliente(Long id) {
        List<SolicitudAbierta> solicitudesAbiertas = this.solicitudesAbiertasDe(id);
        List<SolicitudCerrada> solicitudesCerradas = this.solicitudesCerradasDe(id);
        List<Solicitud> solicitudes = new ArrayList<>(solicitudesAbiertas);
        solicitudes.addAll(solicitudesCerradas);

        return solicitudes.stream().anyMatch(s -> !s.isLeida());
    }

    public boolean algunaSolicitudSinLeerDeAdministrador(Long id) {
        return this.listaAbiertas().stream().anyMatch(s -> !s.isLeida());
    }

}
