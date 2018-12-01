package DDS.SGE;

import java.util.List;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioClientes implements WithGlobalEntityManager {
	private static EntityManager em = EntityManagerHelper.entityManager();
	
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
		return em.find(Cliente.class, id);
	}

	public static Cliente findByUsername(String username) {
		List<Cliente> clientes = em
				.createQuery("from Cliente c where c.username LIKE :username", Cliente.class)
				.setParameter("username", username)
				.getResultList();

		return clientes.get(0);
	}
	
	public static void persistir(Cliente cliente) {
		instancia.agregarCliente(cliente);
	}

}
