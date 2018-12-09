package DDS.SGE.Repositorios;

import DDS.SGE.Solicitud.SolicitudAbierta;
import DDS.SGE.Solicitud.SolicitudCerrada;

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

    public void saveOrUpdate(SolicitudCerrada solicitud) {
        this.persistir(solicitud);
    }

    public void saveOrUpdate(SolicitudAbierta solictud) {
        this.persistir(solictud);
    }

    public static RepositorioSolicitudes getInstance() {
        return instance;
    }
}
