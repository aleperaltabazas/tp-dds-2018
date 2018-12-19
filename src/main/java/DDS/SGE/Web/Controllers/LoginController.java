package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

import DDS.SGE.Exceptions.UserNotFoundException;
import DDS.SGE.Usuarie.Usuario;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.HOME;

public class LoginController extends Controller {
    public ModelAndView logout(Request req, Response res) {
        req.session().invalidate();
        res.redirect(HOME);
        return null;
    }

    static void revisarUsuario(Usuario user, String password) {
        if (!user.getPassword().equalsIgnoreCase(HashProvider.hash(password))) {
            throw new UserNotFoundException(user.getUsername());
        }
    }
}
