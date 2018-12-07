package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static DDS.SGE.Web.Controllers.Routes.*;

public class LoginClienteController extends LoginController {
    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("loginRoute", LOGIN);

        return new ModelAndView(viewModel, "login.hbs");
    }

    public ModelAndView loginCliente(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        try {
            Cliente usuario = RepositorioClientes.getInstance().findByUsername(username).get();

            revisarUsuario(usuario, password);

            String id = Long.toString(usuario.getId());
            res.redirect(HOME);

            req.session().attribute(SESSION_NAME, id);
            req.session().attribute(ADMIN, "no");

            return new ModelAndView(null, "login.hbs");
        } catch (Exception e) {
            return error(req, res);
        }

    }

}