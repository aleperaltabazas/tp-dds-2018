package DDS.SGE.Repositorios;

import DDS.SGE.Dispositivo.DispositivoDeCatalogo;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioCatalogo extends Repositorio {
    private static final RepositorioCatalogo instance = new RepositorioCatalogo();
    private static final int CANTIDAD_POR_PAGINA = 15;

    private RepositorioCatalogo() {
    }

    public DispositivoDeCatalogo findByID(Long id) {
        return findByID(DispositivoDeCatalogo.class, id);
    }

    public void saveOrUpdate(DispositivoDeCatalogo dispositivoDeCatalogo) {
        persistir(dispositivoDeCatalogo);
    }

    private TypedQuery<DispositivoDeCatalogo> getAll() {
        return entityManager().createQuery("from DispositivoDeCatalogo ", DispositivoDeCatalogo.class);
    }

    public List<DispositivoDeCatalogo> listar() {
        return this.getAll().getResultList();
    }

    public List<DispositivoDeCatalogo> listarPagina(int numeroDePagina) {
        return this.getAll().setFirstResult((numeroDePagina - 1) * CANTIDAD_POR_PAGINA).setMaxResults(numeroDePagina * CANTIDAD_POR_PAGINA).getResultList();
    }

    public static RepositorioCatalogo getInstance() {
        return instance;
    }

    public int cantidadDePaginas() {
        return (int) Math.ceil((double) this.getAll().getResultList().size() / CANTIDAD_POR_PAGINA);
    }
}
