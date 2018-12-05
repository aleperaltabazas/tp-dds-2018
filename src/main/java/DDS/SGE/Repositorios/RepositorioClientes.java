package DDS.SGE.Repositorios;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS.SGE.Cliente;

public class RepositorioClientes extends Repositorio  {

    public List<Cliente> getAllClients() {
        return entityManager().createQuery("from Cliente", Cliente.class).getResultList();
    }

    public Cliente findByID(Long id) {
        return findByID(Cliente.class, id);
    }

    public Optional<Cliente> findByUsername(String username) {
        return findByUsername(Cliente.class, username);
    }

    public void agregarCliente(Cliente cliente) {
        persistir(cliente);
    }

    public void registrarCliente(Cliente cliente) throws Exception {
        registrar(cliente, cliente.getUsername());
    }

    public void actualizarCliente(Cliente cliente) {
        persistir(cliente);
    }

}
