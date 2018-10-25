package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioUsuarios {
	List<Usuario> usuarios = new ArrayList<Usuario>(Arrays.asList());
	
	private RepositorioUsuarios() {
	}

	public static RepositorioUsuarios instancia = new RepositorioUsuarios();
	
	public void agregar(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public Usuario buscarSegunNombre(String nombre) {
		//TODO agregar la query (se puede hacer lo que dijo Gaston de tener una superclase repo para no repetir logica)
		return this.usuarios.get(0);
	}

}
