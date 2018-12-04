package DDS.SGE.Repositorios;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS.SGE.Cliente;

public class RepositorioClientes extends Repositorio implements WithGlobalEntityManager {

    // private static RepositorioClientes instancia = new RepositorioClientes();

    public static List<Cliente> getAllClients() {
        return em.createQuery("from Cliente", Cliente.class).getResultList();
    }

    public static Cliente findByID(Long id) {
        return findByID(Cliente.class, id);
    }

    public static Optional<Cliente> findByUsername(String username) {
        return findByUsername(Cliente.class, username);
    }

    public static void agregarCliente(Cliente cliente) {
        persistir(cliente);
    }

    public static void registrarCliente(Cliente cliente) throws Exception {
        registrar(cliente, cliente.getUsername());
    }

    public static void actualizarCliente(Cliente cliente) {
        persistir(cliente);
    }

}
