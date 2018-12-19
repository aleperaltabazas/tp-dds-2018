package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class HomeController extends Controller {
    public ModelAndView home(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            return new ModelAndView(null, "home.hbs");
        }

        if (req.session().attribute(ADMIN) == "si") {
            return new PanelDeAdministradorController().mostrar(req, res);
        } else {
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("mail-icon", this.iconoNotificacionesCliente(Long.parseLong(req.session().attribute(SESSION_NAME))));

            return new ModelAndView(viewModel, "principal.hbs");
        }
    }
}