package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static DDS.SGE.Web.Controllers.Routes.LOGIN;

public class SolicitudesController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("pendientes", RepositorioSolicitudes.getInstance().solicitudesAbiertasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));
        viewModel.put("cerradas", RepositorioSolicitudes.getInstance().solicitudesCerradasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));


        return new ModelAndView(viewModel, "solicitudes.hbs");
    }
}
