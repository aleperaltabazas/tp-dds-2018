package DDS.SGE.Web.Controllers;

import DDS.SGE.Exceptions.AdminNotFoundException;
import DDS.SGE.Exceptions.UserNotFoundException;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Optional;

import static DDS.SGE.Web.Controllers.Routes.ADMINISTRADOR;
import static DDS.SGE.Web.Controllers.Routes.ADMINISTRADOR_LOGIN;

public class LoginAdminController extends LoginController {
    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("loginRoute", ADMINISTRADOR_LOGIN);

        return new ModelAndView(viewModel, "login.hbs");
    }

    public ModelAndView loginAdmin(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Optional<Administrador> admin = RepositorioAdministradores.getInstance().findByUsername(username);

        if (admin.isPresent()) {
            if (!admin.get().getPassword().equalsIgnoreCase(HashProvider.hash(password))) {
                throw new AdminNotFoundException();
            }

            String id = Long.toString(admin.get().getId());

            res.redirect(ADMINISTRADOR);

            req.session().attribute(SESSION_NAME, id);
            req.session().attribute(ADMIN, "si");

            return new PanelDeAdministradorController().mostrar(req, res);
        } else {
            throw new AdminNotFoundException();
        }
    }

    public ModelAndView loginError(Exception e) {
        HashMap<String, Object> viewModel = this.fillError(e);
        return new ModelAndView(viewModel, "login.hbs");
    }
}

