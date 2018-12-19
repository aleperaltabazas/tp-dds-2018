package DDS.SGE.Exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String username) {
		super("El usuario" + username + "no se encontró");
	}
}
