package DDS.SGE.Web.Controllers;

import DDS.SGE.Web.HandlebarsTemplateEngineBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.logging.*;

import static DDS.SGE.Web.Controllers.Routes.*;


public class ErrorController extends Controller {
    private static final Logger logger = Logger.getLogger(ErrorController.class.getName());

    public String notFound(Request req, Response res) {
        String body = HandlebarsTemplateEngineBuilder.create().build().render(new ModelAndView(null, "404.hbs"));
        res.body(body);
        res.status(404);
        return body;
    }

    public ModelAndView unauthorizedAccess(Request req, Response res) {
        logger.log(Level.WARNING, req.ip());
        logger.log(Level.WARNING, req.requestMethod());
        logger.log(Level.WARNING, req.body());

        res.status(403);
        return new ModelAndView(null, "401.hbs");
    }

    public ModelAndView somethingBroke(Request req, Response res) {
        res.status(500);
        return new ModelAndView(null, "500.hbs");
    }
}
