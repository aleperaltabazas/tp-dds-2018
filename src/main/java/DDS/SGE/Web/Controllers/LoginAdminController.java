package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Repositorios.RepositorioAdministradores;
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

        try {
            // No sé que les parezca mejor, dejar el get en el try catch o envolver el
            // optional con un if isEmpty()
            Optional<Administrador> admin = RepositorioAdministradores.getInstance().findByUsername(username);

            if (admin.isPresent()) {
                revisarUsuario(admin.get(), password);

                String id = Long.toString(admin.get().getId());
                System.out.println(id);

                res.redirect(ADMINISTRADOR);

                req.session().attribute(SESSION_NAME, id);
                req.session().attribute(ADMIN, "si");

                return new PanelDeAdministradorController().mostrar(req, res);
            } else {
                throw new RuntimeException("No se encontró esa combinación de usuario y contraseña.");
            }
        } catch (Exception e) {
            HashMap<String, Object> viewModel = this.fillError(e);
            return new ModelAndView(viewModel, "login.hbs");
        }
    }

}
