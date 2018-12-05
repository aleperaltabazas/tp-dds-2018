package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.logging.*;

import static DDS.SGE.Web.Controllers.Routes.*;

public class ErrorController extends Controller {
    private static final Logger logger = Logger.getLogger(ErrorController.class.getName());

    public ModelAndView somethingBroke(Request req, Response res) {
        return new ModelAndView(null, "404.hbs");
    }

    public ModelAndView unauthorizedAccess(Request req, Response res) {
        logger.log(Level.WARNING, req.ip());
        logger.log(Level.WARNING, req.requestMethod());
        logger.log(Level.WARNING, req.body());

        //TODO: pantalla de unauthorized access
        return null;
    }
}
