package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CatalogoController extends Controller {
    public static ModelAndView mostrar(Request req, Response res) {
        return new ModelAndView(null, "catalogo.hbs");
    }
}
