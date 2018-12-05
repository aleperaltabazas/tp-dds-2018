package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TransformadorController {
	public ModelAndView mostrar(Request req, Response res) {
		return new ModelAndView(null, "transformador.hbs");
	}
}
