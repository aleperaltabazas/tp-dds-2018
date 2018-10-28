package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RepositorioClientes {
	List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList());
	EntityManager em = EntityManagerHelper.entityManager();
	
	private RepositorioClientes() {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		clientes = query.getResultList();
	}

	public static RepositorioClientes instancia = new RepositorioClientes();

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public Cliente getCliente (Long idCliente) {
		return clientes.stream().filter(cliente -> cliente.getId() == idCliente).findFirst().orElse(null);
	}
	
}
