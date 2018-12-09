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

        String pantalla;

        HashMap<String, Object> viewModel = new HashMap<>();

        if (req.session().attribute(ADMIN) == "si") {
            pantalla = "solicitudes-administrador.hbs";
            viewModel.put("pendientes", RepositorioSolicitudes.getInstance().listaAbiertas());
            viewModel.put("cerradas", RepositorioSolicitudes.getInstance().listarCerradasPor(Long.parseLong(req.session().attribute(SESSION_NAME))));
        } else {
            pantalla = "solicitudes.hbs";
            viewModel.put("pendientes", RepositorioSolicitudes.getInstance().solicitudesAbiertasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));
            viewModel.put("cerradas", RepositorioSolicitudes.getInstance().solicitudesCerradasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public ModelAndView verSolicitud(Request req, Response res) {
        return null;
    }
}
