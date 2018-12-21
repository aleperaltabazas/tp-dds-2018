package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.HOME;

public class LoginController extends Controller {
    public ModelAndView logout(Request req, Response res) {
        req.session().invalidate();
        res.redirect(HOME);
        return new HomeController().home(req, res);
    }
}
