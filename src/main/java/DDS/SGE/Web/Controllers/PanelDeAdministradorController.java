package DDS.SGE.Web.Controllers;

import DDS.SGE.Exceptions.UnauthorizedAccessException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class PanelDeAdministradorController extends Controller {

    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        HashMap<String, Object> viewModel = this.rellenarAdministrador(null, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "panel-administrador.hbs");
    }
}