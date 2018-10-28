package DDS.SGE.Web.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	public static ModelAndView mostrar(Request req, Response res) {
		System.out.println(req.session().attribute("id").toString());
		
		return new ModelAndView(null, "principal.hbs");
	}
}
