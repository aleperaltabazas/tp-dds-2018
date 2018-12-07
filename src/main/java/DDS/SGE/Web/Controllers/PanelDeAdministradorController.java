package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PanelDeAdministradorController extends Controller {

    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        return new ModelAndView(null, "panelAdministrador.hbs");
    }

    public ModelAndView verTodosLosHogares(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        return new ModelAndView(null, "listado.hbs");
    }
}