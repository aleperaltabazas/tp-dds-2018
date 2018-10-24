package DDS.SGE.Web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import DDS.SGE.*;
import DDS.SGE.Regla.*;
import DDS.SGE.Web.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
	public static void main(String[] args) {
		Spark.port(9000);
		//System.getProperty("user.dir") = C:\Users\Usuario\Desktop\DDS-TP\Repo\2018-vn-group-11\DDS\SGE
		Spark.staticFiles.externalLocation(System.getProperty("user.dir")); //external porque jetty no me updatea las static files al hacer cambios
		DebugScreen.enableDebugScreen();

		HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
		HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

		get("/", HomeController::mostrar, engine);	
	}
}
