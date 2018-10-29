package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.mysql.cj.Query;

public class RepositorioClientes implements WithGlobalEntityManager{
	
	public static RepositorioClientes instancia = new RepositorioClientes();

	public void agregarCliente(Cliente cliente) {
		entityManager().persist(cliente);
	}
	
	public List<Cliente> getClientes() {
		return entityManager().createQuery("from Cliente", Cliente.class)
		.getResultList();
	}
	
	public Cliente getCliente (Long id) {
		return entityManager().find(Cliente.class, id);
	}
	
	public Cliente findByUsername(String username) {
		EntityManager em = EntityManagerHelper.entityManager();
		
		TypedQuery<Cliente> query = em.createQuery("SELECT c.nombre FROM Cliente c where c.nombre = " + username, Cliente.class);
		return query.getSingleResult();
	}
	
}

