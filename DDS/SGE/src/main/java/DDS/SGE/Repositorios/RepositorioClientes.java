package DDS.SGE.Repositorios;

import java.util.List;
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

	public static Cliente findByUsername(String username) {
		return findByUsername(Cliente.class, username);
	}

	public static void agregarCliente(Cliente cliente) {
		persistir(cliente);
	}

}
