package DDS.SGE.Exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String username) {
		super("El usuario" + username + "no se encontró");
	}
}
