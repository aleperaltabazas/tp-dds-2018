package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RepositorioUsuarios {
	List<Usuario> usuarios = new ArrayList<Usuario>(Arrays.asList());
	//EntityManager em = EntityManagerHelper.entityManager();
	
	private RepositorioUsuarios() {
		//TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		//usuarios = query.getResultList();
	}

	public static RepositorioUsuarios instancia = new RepositorioUsuarios();
	
	public void agregar(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public Usuario buscarSegunNombre(String nombre) {
		//TODO agregar la query (se puede hacer lo que dijo Gaston de tener una superclase repo para no repetir logica)
		return this.usuarios.get(0);
	}
	
	public Usuario buscarSegunId (Long idUsuario) {
		return this.usuarios.stream().filter(usuario -> usuario.getId() == idUsuario).findFirst().orElse(null);
	}
}
