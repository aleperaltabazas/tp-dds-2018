package DDS.SGE.Repositorios;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;

import java.util.List;

public class RepositorioDispositivos extends Repositorio {
    private static final RepositorioDispositivos instance = new RepositorioDispositivos();

    private RepositorioDispositivos() {
    }

    public List<Dispositivo> listar() {
        return entityManager().createQuery("from Dispositivo", Dispositivo.class).getResultList();
    }

    public Dispositivo findByID(Long id) {
        return this.findByID(Dispositivo.class, id);
    }

    public void saveOrUpdate(Dispositivo dispositivo) {
        persistir(dispositivo);
    }

    public static RepositorioDispositivos getInstance() {
        return instance;
    }
}
