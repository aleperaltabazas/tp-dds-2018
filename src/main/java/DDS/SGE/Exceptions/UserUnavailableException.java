package DDS.SGE.Exceptions;

import DDS.SGE.Usuarie.Cliente;

public class UserUnavailableException extends RuntimeException {
    private Cliente cliente;

    public UserUnavailableException(Cliente cliente) {
        super("Lo sentimos, el nombre de usuario " + cliente.getUsername() + " no se encuentra disponible");
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
