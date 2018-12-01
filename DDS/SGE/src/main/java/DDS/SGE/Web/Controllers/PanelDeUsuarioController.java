package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Response;
import spark.Request;

public class PanelDeUsuarioController {
	public static ModelAndView mostrar(Request req, Response res) {
		return new ModelAndView(null, "panelDeUsuario.hbs");
	}
}
