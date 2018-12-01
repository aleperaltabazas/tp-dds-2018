package DDS.SGE.Repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import DDS.SGE.Administrador;

public class RepositorioAdministradores extends Repositorio implements WithGlobalEntityManager {
	// private static RepositorioAdministradores instancia = new
	// RepositorioAdministradores();

	public static void agregarAdministrador(Administrador administrador) {
		persistir(administrador);
	}

	public static Administrador findByUsername(String username) {
		return findByUsername(Administrador.class, username);
	}

	public static Administrador findByID(Long id) {
		return findByID(Administrador.class, id);
	}
}
