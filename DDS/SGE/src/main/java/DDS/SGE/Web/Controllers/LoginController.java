package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

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
		String password = req.queryParams("password");
		
		Usuario usuario = RepositorioUsuarios.instancia.buscarSegunNombre(username);

		if(username.equals("admin")) {
			res.redirect("/administrador");
		}
		
		req.session().attribute(SESSION_NAME);
		
		if(usuario.getPassword().equals(password)) {
			//Se deberia hacer con el Id
			res.redirect("/user/" + usuario.getUsername());
			req.session().attribute(SESSION_NAME, usuario.getUsername());
			
		} else {
			//Se podria mostrar un "error en el login"
			Map<String, Object> viewmodel = new HashMap();
			viewmodel.put("username", username);
			viewmodel.put("password", password);
			return new ModelAndView(viewmodel, "loginError.hbs");
		}
	
		return new ModelAndView(null, "login.hbs");
	}
	
}