package DDS.SGE.Repositorios;

import java.util.List;
import java.util.Optional;

import DDS.SGE.Usuarie.Administrador;

public class RepositorioAdministradores extends Repositorio {
    private static final RepositorioAdministradores instance = new RepositorioAdministradores();

    private RepositorioAdministradores() {
    }

    public void agregarAdministrador(Administrador administrador) {
        this.persistir(administrador);
    }

    public Optional<Administrador> findByUsername(String username) {
        return this.findByUsername(Administrador.class, username);
    }

    public Administrador findByID(Long id) {
        return this.findByID(Administrador.class, id);
    }

    public void registrarAdministrador(Administrador administrador) {
        this.registrar(administrador, administrador.getUsername());
    }

    public static RepositorioAdministradores getInstance() {
        return instance;
    }

    public void saveOrUpdate(Administrador administrador) {
        persistir(administrador);
    }

    public List<Administrador> listar() {
        return this.findAll(Administrador.class);
    }
}
