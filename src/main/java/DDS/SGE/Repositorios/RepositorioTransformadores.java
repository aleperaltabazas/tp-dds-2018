package DDS.SGE.Repositorios;

import DDS.SGE.Geoposicionamiento.Transformador;

import java.util.List;

public class RepositorioTransformadores extends Repositorio {
    private static final RepositorioTransformadores instance = new RepositorioTransformadores();

    private RepositorioTransformadores() {
    }

    public List<Transformador> listar() {
        return findAll(Transformador.class);
    }

    public void agregarTransformador(Transformador transformador) {
        persistir(transformador);
    }

    public static RepositorioTransformadores getInstance() {
        return instance;
    }

    public Transformador findByID(Long id) {
        return findByID(id);
    }
}
