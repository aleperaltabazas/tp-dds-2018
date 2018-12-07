package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.ADMINISTRADOR;

public class LoginAdminController extends LoginController {
    public ModelAndView mostrar(Request req, Response res) {
        // Tal vez estaría bueno tener una pantalla
        // de login distinta
        return new ModelAndView(null, "login-admin.hbs");
    }

    public ModelAndView loginAdmin(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        try {
            // No sé que les parezca mejor, dejar el get en el try catch o envolver el
            // optional con un if isEmpty()
            Administrador admin = RepositorioAdministradores.getInstance().findByUsername(username).get();

            revisarUsuario(admin, password);

            String id = Long.toString(admin.getId());
            res.redirect(ADMINISTRADOR);

            req.session().attribute(SESSION_NAME, id);
            req.session().attribute(ADMIN, "si");

            return new PanelDeAdministradorController().mostrar(req, res);
        } catch (Exception e) {
            return error(req, res);
        }
    }

}
