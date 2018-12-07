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

    public List<Dispositivo> catalogoDeDispositivos() {
        return entityManager().createQuery("from Dispositivo d where d.esDeCatalogo = true", Dispositivo.class).getResultList();
    }

    public void agregarDispositivoAlCatalogo(Dispositivo dispositivo) {
        dispositivo.setEsDeCatalogo(true);
        persistir(dispositivo);
    }

    public void foo(DispositivoDeCatalogo dispositivoDeCatalogo) {
        persistir(dispositivoDeCatalogo);
    }

    public DispositivoDeCatalogo buscarEnElCatalogoPorID(Long id) {
        return this.findByID(DispositivoDeCatalogo.class, id);
    }

    public Dispositivo findByID(Long id) {
        return this.findByID(Dispositivo.class, id);
    }

    public void guardarDispositivo(Dispositivo dispositivo) {
        persistir(dispositivo);
    }

    public static RepositorioDispositivos getInstance() {
        return instance;
    }
}
