package DDS.SGE.Exceptions;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException() {
        super("No se encontró la combinación de usuario y contraseña");
    }
}
