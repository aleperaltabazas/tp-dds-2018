package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class PanelDeAdministradorController extends Controller {

    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(Long.parseLong(req.session().attribute(SESSION_NAME))));

        return new ModelAndView(viewModel, "panelAdministrador.hbs");
    }

    public ModelAndView verTodosLosHogares(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(Long.parseLong(req.session().attribute(SESSION_NAME))));

        return new ModelAndView(viewModel, "listado.hbs");
    }
}