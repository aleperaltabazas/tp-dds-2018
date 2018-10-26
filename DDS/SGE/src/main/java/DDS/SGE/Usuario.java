package DDS.SGE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	
	String username;
	String password;
	@OneToOne
	Cliente cliente;
	
	public Usuario(String username, String password, Cliente cliente) {
		this.username = username;
		this.password = password;
		this.cliente = cliente;
		RepositorioUsuarios.instancia.agregar(this);
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Long getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
}
