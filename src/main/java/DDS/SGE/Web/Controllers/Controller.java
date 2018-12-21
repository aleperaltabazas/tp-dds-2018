package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Controller implements WithGlobalEntityManager, TransactionalOps {
    protected static final String SESSION_NAME = "id";
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    static final String ADMIN = "admin";

    HashMap<String, Object> rellenarCliente(HashMap<String, Object> viewModel, String id) {
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        if (viewModel == null)
            viewModel = new HashMap<>();

        viewModel.put("nombre", cliente.getNombre());
        viewModel.put("apellido", cliente.getApellido());
        viewModel.put("telefono", cliente.getTelefono());
        viewModel.put("numeroDni", cliente.getNumeroDni());
        viewModel.put("tipoDni", cliente.getTipoDni());
        viewModel.put("domicilio", cliente.getDomicilio());
        viewModel.put("username", cliente.getUsername());
        viewModel.put("user", cliente);

        return viewModel;
    }

    protected HashMap fillError(Exception e) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("errorMessage", e.getMessage());

        return viewModel;
    }

    HashMap<String, Object> rellenarAdministrador(HashMap<String, Object> viewModel, String id) {
        if (viewModel == null) {
            viewModel = new HashMap<>();
        }

        Administrador admin = RepositorioAdministradores.getInstance().findByID(Long.parseLong(id));

        viewModel.put("nombre", admin.getNombre());
        viewModel.put("apellido", admin.getApellido());
        viewModel.put("direccion", admin.getDomicilio());
        viewModel.put("username", admin.getUsername());
        viewModel.put("fechaDeAlta", admin.getFechaAltaSistema().toString());
        viewModel.put("user", admin);

        return viewModel;
    }

    protected void logError(Exception e) {
        LOGGER.log(Level.INFO, e.getMessage(), e);
    }

    protected void logInfo(String info) {
        LOGGER.log(Level.INFO, info);
    }
}
