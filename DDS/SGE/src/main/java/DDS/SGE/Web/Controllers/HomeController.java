package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	protected static final String SESSION_NAME = "id";

	public static ModelAndView mostrar(Request req, Response res) {
		if (req.session().attribute(SESSION_NAME) == null) {
			return new ModelAndView(null, "home.hbs");
		}

		return new ModelAndView(null, "principal.hbs");
	}

}