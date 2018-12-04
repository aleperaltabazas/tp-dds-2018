package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

import DDS.SGE.Usuario;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.HOME;

public class LoginController extends Controller {

    protected static ModelAndView error(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Map<String, Object> viewmodel = new HashMap<String, Object>();
        viewmodel.put("username", username);
        viewmodel.put("password", password);
        return new ModelAndView(viewmodel, "loginError.hbs");
    }

    public static ModelAndView logout(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
        }

        req.session().invalidate();
        res.redirect(HOME);
        return null;
    }

    protected static void revisarUsuario(Usuario user, String password) {
        if (!user.getPassword().equalsIgnoreCase(HashProvider.hash(password))) {
            throw new RuntimeException("No matcheó usuario y contraseña");
        }
    }
}
