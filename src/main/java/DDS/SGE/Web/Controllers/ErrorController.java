package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.HOME;

public class ErrorController extends Controller {
    public ModelAndView somethingBroke(Request req, Response res) {
        return new ModelAndView(null, "404.hbs");
    }
}
