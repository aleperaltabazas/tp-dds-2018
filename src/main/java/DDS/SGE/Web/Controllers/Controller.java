package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Controller implements WithGlobalEntityManager, TransactionalOps {
    protected static final String SESSION_NAME = "id";
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    static final String ADMIN = "admin";

    HashMap<String, Object> rellenarCliente(Cliente cliente) {
        HashMap<String, Object> viewModel = new HashMap<>();

        viewModel.put("nombre", cliente.getNombre());
        viewModel.put("apellido", cliente.getApellido());
        viewModel.put("telefono", cliente.getTelefono());
        viewModel.put("numeroDni", cliente.getNumeroDni());
        viewModel.put("tipoDni", cliente.getTipoDni());
        viewModel.put("domicilio", cliente.getDomicilio());
        viewModel.put("username", cliente.getUsername());

        return viewModel;
    }

    public ModelAndView fortyTwo(Request request, Response response) {
        return new ModelAndView(null, "meaning-of-life.hbs");
    }

    protected HashMap fillError(Exception e) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("errorMessage", e.getMessage());

        return viewModel;
    }

    protected String iconoNotificacionesAdministrador(Long id) {
        if (RepositorioAdministradores.getInstance().findByID(id).getTieneNotificaciones()) {
            return "mail-icon-notification.png";
        } else {
            return "mail-icon.png";
        }
    }

    protected String iconoNotificacionesCliente(Long id) {
        if (RepositorioClientes.getInstance().findByID(id).getTieneNotificaciones()) {
            return "mail-icon-notification.png";
        } else {
            return "mail-icon.png";
        }
    }

    protected void logError(Exception e) {
        LOGGER.log(Level.INFO, e.getMessage(), e);
    }

    protected void logInfo(String info) {
        LOGGER.log(Level.INFO, info);
    }
}
