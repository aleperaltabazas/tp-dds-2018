package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PanelAdministradorController {
	
	public static ModelAndView mostrar(Request req, Response res) {
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "panelAdministrador.hbs");
	}
	
	public static ModelAndView verTodosLosHogares(Request req, Response res) {
		
		return new ModelAndView(null, "listado.hbs");
	}
	


}