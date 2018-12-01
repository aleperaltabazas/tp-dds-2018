package DDS.SGE.Repositorios;

import java.util.List;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS.SGE.Cliente;
import DDS.SGE.EntityManagerHelper;

public class RepositorioClientes extends Repositorio implements WithGlobalEntityManager {

	public static RepositorioClientes instancia = new RepositorioClientes();

	private void agregarCliente(Cliente cliente) {
		EntityManagerHelper.beginTransaction();
		em.persist(cliente);
		EntityManagerHelper.commit();
	}

	public static List<Cliente> getAllClients() {
		return em.createQuery("from Cliente", Cliente.class).getResultList();
	}

	public static Cliente findByID(Long id) {
		return findByID(Cliente.class, id);
	}

	public static Cliente findByUsername(String username) {
		return findByUsername(Cliente.class, username);
	}

	public static void persistir(Cliente cliente) {
		instancia.agregarCliente(cliente);
	}

}
