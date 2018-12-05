package DDS.SGE.Repositorios;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;

import java.util.List;

public class RepositorioDispositivos extends Repositorio {
    private static final RepositorioDispositivos instance = new RepositorioDispositivos();

    private RepositorioDispositivos() {
    }

    public List<Dispositivo> listar() {
        return entityManager().createQuery("from Dispositivo", Dispositivo.class).getResultList();
    }

    public List<Dispositivo> catalogoDeDispositivos() {
        return entityManager().createQuery("from Dispositivo d where d.esDeCatalogo = true", Dispositivo.class).getResultList();
    }

    public void agregarDispositivoAlCatalogo(Dispositivo dispositivo) {
        dispositivo.setEsDeCatalogo(true);
        persistir(dispositivo);
    }

    public static RepositorioDispositivos getInstance() {
        return instance;
    }
}
