package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class HogaresController extends Controller {
    public ModelAndView verTodosLosHogares(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("clientes", RepositorioClientes.getInstance().getAllClients());
        viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(Long.parseLong(req.session().attribute(SESSION_NAME))));

        return new ModelAndView(viewModel, "hogares.hbs");
    }

    public ModelAndView hogarDe(Request req, Response res) {
        return null;
    }
}
