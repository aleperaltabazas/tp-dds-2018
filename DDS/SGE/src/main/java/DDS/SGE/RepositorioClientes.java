package DDS.SGE;

import java.util.List;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioClientes implements WithGlobalEntityManager {

	public static RepositorioClientes instancia = new RepositorioClientes();

	public void agregarCliente(Cliente cliente) {
		entityManager().persist(cliente);
	}

	public List<Cliente> getClientes() {
		return entityManager().createQuery("from Cliente", Cliente.class).getResultList();
	}

	public Cliente getCliente(Long id) {
		return entityManager().find(Cliente.class, id);
	}

	public Cliente findByUsername(String username) {
		EntityManager em = EntityManagerHelper.entityManager();

		List<Cliente> clientes = em
				.createQuery("SELECT u.username FROM Usuario u where u.username LIKE :_username ", Cliente.class)
				.setParameter("_username", username).setMaxResults(1).getResultList();

		return clientes.get(0);
	}

}
