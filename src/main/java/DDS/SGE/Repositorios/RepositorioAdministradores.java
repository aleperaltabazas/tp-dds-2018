package DDS.SGE.Repositorios;

import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import DDS.SGE.Administrador;

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
}
