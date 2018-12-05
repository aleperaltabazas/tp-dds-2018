package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller {
    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            return new ModelAndView(null, "home.hbs");
        }

        return homeLogeado(req, res);
    }

    private static ModelAndView homeLogeado(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "si") {
            return PanelDeAdministradorController.mostrar(req, res);
        } else {
            return new ModelAndView(null, "principal.hbs");
        }
    }
}