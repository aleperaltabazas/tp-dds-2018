package DDS.SGE.Repositorios;

import java.util.List;
import java.util.Optional;

import DDS.SGE.Usuarie.Cliente;

public class RepositorioClientes extends Repositorio {
    private static final RepositorioClientes instance = new RepositorioClientes();

    private RepositorioClientes() {
    }

    public List<Cliente> getAllClients() {
        return entityManager().createQuery("from Cliente", Cliente.class).getResultList();
    }

    public Cliente findByID(Long id) {
        return this.findByID(Cliente.class, id);
    }

    public Optional<Cliente> findByUsername(String username) {
        return this.findByUsername(Cliente.class, username);
    }

    public void agregarCliente(Cliente cliente) {
        this.persistir(cliente);
    }

    public void registrarCliente(Cliente cliente) {
        this.registrar(cliente, cliente.getUsername());
    }

    public void actualizarCliente(Cliente cliente) {
        this.persistir(cliente);
    }

    public static RepositorioClientes getInstance() {
        return instance;
    }
}
