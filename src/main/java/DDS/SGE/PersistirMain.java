package DDS.SGE;

import DDS.SGE.Dispositivo.TablaDispositivos;
import DDS.SGE.Repositorios.RepositorioClientes;

public class PersistirMain {
	// La única manera que se me ocurre para andar persistiendo cosas a la base

	public static void main(String[] args) {
		ClienteBuilder cb = new ClienteBuilder();

		Cliente c1 = cb.crearCliente("Alejandro", "Peralta Bazas", "4012972", "16729076", "Alesaurio", "pass");
		Cliente c2 = cb.crearCliente("Matias", "Giorda", "12927397", "47820726", "maticrash", "otrapass");
		
		TablaDispositivos t = new TablaDispositivos();

		try {
			RepositorioClientes.registrarCliente(c1);
			RepositorioClientes.registrarCliente(c2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Cliente p = RepositorioClientes.findByUsername(c1.getUsername()).get();
		//System.out.println(p.getUsername());
		//System.out.println(p.getNombre());
		System.exit(0);

	}
}
