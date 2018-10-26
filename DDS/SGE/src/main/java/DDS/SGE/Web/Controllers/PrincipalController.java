package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PrincipalController {
	
	public static ModelAndView mostrar(Request req, Response res) {
		//Obtener la direccion correspondiente del hbs, ruta comienza en main/resources/templates/
		return new ModelAndView(null, "principal.hbs");
	}
	
}