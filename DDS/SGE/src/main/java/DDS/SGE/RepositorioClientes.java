package DDS.SGE;

import java.util.List;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioClientes implements WithGlobalEntityManager {
	EntityManager em = EntityManagerHelper.entityManager();
	
	public static RepositorioClientes instancia = new RepositorioClientes();

	public void agregarCliente(Cliente cliente) {
		EntityManagerHelper.beginTransaction();
		em.persist(cliente);
		EntityManagerHelper.commit();
	}

	public List<Cliente> getClientes() {
		return em.createQuery("from Cliente", Cliente.class).getResultList();
	}

	public Cliente getCliente(Long id) {
		return em.find(Cliente.class, id);
	}

	public Cliente findByUsername(String username) {
		List<Cliente> clientes = em
				.createQuery("from Cliente c where c.username LIKE :username", Cliente.class)
				.setParameter("username", username)
				.getResultList();

		return clientes.get(0);
	}

}
