package DDS.SGE.Web;

import DDS.SGE.RepositorioUsuarios;
import DDS.SGE.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	public static ModelAndView mostrar(Request req, Response res) {
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView loggear(Request req, Response res) {
		Usuario usuario = RepositorioUsuarios.instancia.buscarSegunNombre(req.params("usuario"));
		if(usuario.getPassword().equals(req.params("password"))) {
			res.redirect("/");
		}
		else {
			res.redirect("login");
		}
		req.session().attribute("usuarioId", usuario.getId());
		return new ModelAndView(null, "login.hbs");
	}
}