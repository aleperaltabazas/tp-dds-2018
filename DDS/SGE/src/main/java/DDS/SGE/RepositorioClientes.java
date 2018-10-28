package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

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
}

