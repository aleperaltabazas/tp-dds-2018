package DDS.SGE.Exceptions;

public class UserUnavailableException extends RuntimeException {
    public UserUnavailableException(String username) {
        super("Lo sentimos, el nombre de usuario " + username + " no se encuentra disponible");
    }
}
