package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

import DDS.SGE.Administrador;
import DDS.SGE.Cliente;
import DDS.SGE.RepositorioAdministradores;
import DDS.SGE.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginClienteController {
	
	private static final String SESSION_NAME = "id";
	
	public static ModelAndView mostrar(Request req, Response res) {
		
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {

		String username = req.queryParams("username");
		String password = req.queryParams("password");
		
		Cliente usuario = RepositorioClientes.instancia.findByUsername(username);
		
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
	
	public static ModelAndView loginAdmin(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		
		Administrador admin = RepositorioAdministradores.instancia.findByUsername(username);
		
		req.session().attribute(SESSION_NAME);
		
		if(admin.getPassword()!= password) {
			Map<String, Object> viewmodel = new HashMap<String, Object>();
			viewmodel.put("username", username);
			viewmodel.put("password", password);
			return new ModelAndView(viewmodel, "loginError.hbs");
		} else {
			String id = Long.toString(admin.getId());
			res.redirect("/administrador/" + id);
			req.session().attribute(SESSION_NAME, id);
		}
		
		return new ModelAndView(null, "login.hbs");
	}
}