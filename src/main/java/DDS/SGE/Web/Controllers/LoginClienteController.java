package DDS.SGE.Web.Controllers;

import DDS.SGE.Exceptions.UserNotFoundException;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.NoSuchElementException;
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

        Optional<Cliente> cliente = RepositorioClientes.getInstance().findByUsername(username);
        if (cliente.isPresent()) {

            if (!cliente.get().getPassword().equalsIgnoreCase(HashProvider.hash(password))) {
                return this.loginError(new UserNotFoundException());
            }

            String id = Long.toString(cliente.get().getId());

            req.session().attribute(SESSION_NAME, id);
            req.session().attribute(ADMIN, "no");

            res.redirect(HOME);

        } else {
            return this.loginError(new UserNotFoundException());
        }

        return new HomeController().home(req, res);
    }

    public ModelAndView loginError(UserNotFoundException e) {
        HashMap<String, Object> viewModel = this.fillError(e);
        return new ModelAndView(viewModel, "login.hbs");
    }
}