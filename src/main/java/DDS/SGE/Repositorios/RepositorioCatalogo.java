package DDS.SGE.Repositorios;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;

import java.util.List;

public class RepositorioCatalogo extends Repositorio {
    private static final RepositorioCatalogo instance = new RepositorioCatalogo();

    private RepositorioCatalogo() {
    }

    public DispositivoDeCatalogo findByID(Long id) {
        return findByID(DispositivoDeCatalogo.class, id);
    }

    public void agregarDispositivoAlCatalogo(DispositivoDeCatalogo dispositivoDeCatalogo) {
        persistir(dispositivoDeCatalogo);
    }

    public List<DispositivoDeCatalogo> listar() {
        return entityManager().createQuery("from DispositivoDeCatalogo ", DispositivoDeCatalogo.class).getResultList();
    }

    public static RepositorioCatalogo getInstance() {
        return instance;
    }
}
