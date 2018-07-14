package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioClientes {
	List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList());

	private RepositorioClientes() {

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
}
