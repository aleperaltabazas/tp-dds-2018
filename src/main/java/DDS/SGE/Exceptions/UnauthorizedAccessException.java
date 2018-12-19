package DDS.SGE.Exceptions;

import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import spark.Request;

public class UnauthorizedAccessException extends RuntimeException {
    private Cliente cliente;
    private Request request;

    public UnauthorizedAccessException(Long id, Request request) {
        super("Cliente de ID " + RepositorioClientes.getInstance().findByID(id).getUsername() + " en la IP" + request.ip());
        this.cliente = RepositorioClientes.getInstance().findByID(id);
        this.request = request;
    }
}
