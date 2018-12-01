package DDS.SGE.Repositorios;

import java.util.List;
import java.util.NoSuchElementException;

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
		return findByUsername(Cliente.class, username).get();
	}

	public static void agregarCliente(Cliente cliente) {
		persistir(cliente);
	}

	public static void registrarCliente(Cliente cliente) throws Exception {
		try {
			findByUsername(cliente.getUsername());
			throw new Exception("Ese nombre de usuario no se encuentra disponible");
		} catch (NoSuchElementException e) {
			agregarCliente(cliente);
		}
	}

}
