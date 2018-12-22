package DDS.SGE.Repositorios;

import java.util.List;
import java.util.Optional;

import DDS.SGE.Usuarie.Cliente;

import javax.persistence.TypedQuery;

public class RepositorioClientes extends Repositorio {
    private static final RepositorioClientes instance = new RepositorioClientes();
    private static final int CANTIDAD_POR_PAGINA = 15;

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

    public void saveOrUpdate(Cliente cliente) {
        this.persistir(cliente);
    }

    public void registrarCliente(Cliente cliente) {
        this.registrar(cliente, cliente.getUsername());
    }

    private TypedQuery<Cliente> getAll() {
        return entityManager().createQuery("from Cliente", Cliente.class);
    }

    public static RepositorioClientes getInstance() {
        return instance;
    }

    public List<Cliente> listarPagina(int numeroDePagina) {
        return this.getAll().setFirstResult((numeroDePagina - 1) * CANTIDAD_POR_PAGINA).setMaxResults(numeroDePagina * CANTIDAD_POR_PAGINA).getResultList();
    }

    public int cantidadDePaginas() {
        return (int) Math.ceil((double) this.getAll().getResultList().size() / CANTIDAD_POR_PAGINA);
    }
}
