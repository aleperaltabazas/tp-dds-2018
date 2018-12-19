package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Optional;

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
            Optional<Cliente> usuario = RepositorioClientes.getInstance().findByUsername(username);
            if (usuario.isPresent()) {

                revisarUsuario(usuario.get(), password);

                String id = Long.toString(usuario.get().getId());
                res.redirect(HOME);

                req.session().attribute(SESSION_NAME, id);
                req.session().attribute(ADMIN, "no");

                return new HomeController().home(req, res);
            } else {
                throw new RuntimeException("No se encontró esa combinación de usuario y contraseña");
            }

        } catch (Exception e) {
            HashMap<String, Object> viewModel = this.fillError(e);
            return new ModelAndView(viewModel, "login.hbs");
        }

    }

}