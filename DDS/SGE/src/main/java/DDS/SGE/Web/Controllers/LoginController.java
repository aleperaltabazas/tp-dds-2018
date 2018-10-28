package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

import DDS.SGE.RepositorioUsuarios;
import DDS.SGE.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	private static final String SESSION_NAME = "id";
	
	public static ModelAndView mostrar(Request req, Response res) {
		
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {

		String username = req.queryParams("username");
		String password = req.queryParams("password");
		
		Usuario usuario = RepositorioUsuarios.instancia.buscarSegunNombre(username);
		
		req.session().attribute(SESSION_NAME);
		
		if(usuario.getPassword().equals(password)) {
			String id = Long.toString(usuario.getId());
			res.redirect("/user/" + id);
			req.session().attribute(SESSION_NAME, id);			
		} 
		
		else {
			Map<String, Object> viewmodel = new HashMap<String, Object>();
			viewmodel.put("username", username);
			viewmodel.put("password", password);
			return new ModelAndView(viewmodel, "loginError.hbs");
		}
	
		return new ModelAndView(null, "login.hbs");
	}	
}