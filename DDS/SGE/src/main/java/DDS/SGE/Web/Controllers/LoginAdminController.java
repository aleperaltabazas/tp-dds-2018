package DDS.SGE.Web.Controllers;

import DDS.SGE.Administrador;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginAdminController extends LoginController {
	public static ModelAndView mostrar(Request req, Response res) {
		// Tal vez estaría bueno tener una pantalla
		// de login distinta
		return new ModelAndView(null, "login.hbs");
	}

	public static ModelAndView loginAdmin(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		try {
			Administrador admin = RepositorioAdministradores.findByUsername(username);

			req.session().attribute(SESSION_NAME);

			if (admin.getPassword() != HashProvider.hash(password)) {
				return error(req, res);
			} else {
				String id = Long.toString(admin.getId());
				res.redirect("/administrador/" + id);
				req.session().attribute(SESSION_NAME, id);
			}
		} catch (Exception e) {
			return error(req, res);
		}

		return new ModelAndView(null, "login.hbs");
	}

}
