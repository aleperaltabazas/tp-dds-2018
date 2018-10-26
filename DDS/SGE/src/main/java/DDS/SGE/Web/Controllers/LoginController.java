package DDS.SGE.Web.Controllers;

import DDS.SGE.RepositorioUsuarios;
import DDS.SGE.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	private static final String SESSION_NAME = "username";
	
	public static ModelAndView mostrar(Request req, Response res) {
		
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {
		String username = req.queryParams("username");
		
		Usuario usuario = RepositorioUsuarios.instancia.buscarSegunNombre(username);
		
		if(username.equals("admin")) {
			res.redirect("/administrador");
		}
		
		req.session().attribute(SESSION_NAME);
		
		if(usuario.getPassword().equals(req.queryParams("password"))) {
			//Se deberia hacer con el Id
			res.redirect("/user/" + usuario.getUsername());
			req.session().attribute(SESSION_NAME, usuario.getUsername());
			
		}
		else {
			//Se podria mostrar un "error en el login"
			return new ModelAndView(null, "loginError.hbs");
		}
	
		return new ModelAndView(null, "login.hbs");
	}
	
}