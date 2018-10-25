package DDS.SGE.Web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import DDS.SGE.*;
import DDS.SGE.Cliente.TipoDni;
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
		Spark.staticFiles.location("/public");
		DebugScreen.enableDebugScreen();

		HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
		HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();
		
		Usuario usuarioPrueba = new Usuario("matigiorda", "123", 
				new Cliente("Matias", "Giorda", TipoDni.DNI, "123454321", "1155667788",
			"Calle verdadera 321", LocalDateTime.now(), Arrays.asList()));

		get("/", HomeController::mostrar, engine);	
		get("/login", LoginController::mostrar, engine);
		post("/login", LoginController::loggear, engine);
		get("/user/:id", UserController::mostrar, engine);
	}
}
