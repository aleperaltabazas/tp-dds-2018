package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
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
            HashMap<String, Object> viewModel = this.rellenarCliente(null, req.session().attribute(SESSION_NAME));

            return new ModelAndView(viewModel, "principal.hbs");
        }
    }
}