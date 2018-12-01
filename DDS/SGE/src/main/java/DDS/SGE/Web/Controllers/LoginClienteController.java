package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginClienteController extends LoginController {
	public static ModelAndView mostrar(Request req, Response res) {
		// Obtener la direccion correspondiente del hbs, ruta comienza en
		// main/resources/templates/
		return new ModelAndView(null, "login.hbs");
	}

	public static ModelAndView loginCliente(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		try {
			// No sé que les parezca mejor, dejar el get en el try catch o envolver el
			// optional con un if isEmpty()
			Cliente usuario = RepositorioClientes.findByUsername(username).get();

			req.session().attribute(SESSION_NAME);

			if (!usuario.getPassword().equalsIgnoreCase(HashProvider.hash(password))) {
				return error(req, res);
			} else {
				String id = Long.toString(usuario.getId());
				res.redirect("/user/" + id);
				req.session().attribute(SESSION_NAME, id);
			}
		} catch (Exception e) {
			return error(req, res);
		}

		return new ModelAndView(null, "login.hbs");
	}

}