package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.HOME;

public class GlitchController extends Controller {
    public static ModelAndView somethingBroke(Request req, Response res) {
        res.redirect(HOME);
        return new ModelAndView(null, "glitch.hbs");
    }
}
