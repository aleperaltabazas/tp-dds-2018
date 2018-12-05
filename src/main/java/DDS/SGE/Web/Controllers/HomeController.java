package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            return new ModelAndView(null, "home.hbs");
        }

        return homeLogeado(req, res);
    }

    private ModelAndView homeLogeado(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "si") {
            return new PanelDeAdministradorController().mostrar(req, res);
        } else {
            return new ModelAndView(null, "principal.hbs");
        }
    }
}