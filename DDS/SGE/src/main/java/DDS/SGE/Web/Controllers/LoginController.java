package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	protected static final String SESSION_NAME = "id";

	protected static ModelAndView error(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		Map<String, Object> viewmodel = new HashMap<String, Object>();
		viewmodel.put("username", username);
		viewmodel.put("password", password);
		return new ModelAndView(viewmodel, "loginError.hbs");
	}
}
