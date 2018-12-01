package DDS.SGE.Repositorios;

import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import DDS.SGE.Administrador;
import DDS.SGE.EntityManagerHelper;

public class RepositorioAdministradores extends Repositorio implements WithGlobalEntityManager {
	private static RepositorioAdministradores instancia = new RepositorioAdministradores();

	private void agregarAdministrador(Administrador administrador) {
		EntityManagerHelper.beginTransaction();
		em.persist(administrador);
		EntityManagerHelper.commit();
	}

	public Administrador findByUsername(String username) {
		List<Administrador> administradores = em
				.createQuery("from Cliente c where c.username LIKE :username", Administrador.class)
				.setParameter("username", username).getResultList();

		return administradores.get(0);
	}
	
	public static Administrador findByID(Long id) {
		return findByID(Administrador.class, id);
	}

	public static void persistir(Administrador administrador) {
		instancia.agregarAdministrador(administrador);
	}
}
