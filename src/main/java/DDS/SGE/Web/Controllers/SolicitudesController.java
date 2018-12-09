package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class SolicitudesController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();


        return new ModelAndView(viewModel, "solicitudes.hbs");
    }
}
