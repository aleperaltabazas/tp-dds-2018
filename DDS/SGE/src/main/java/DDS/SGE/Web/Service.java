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
import DDS.SGE.Web.Controllers.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
	public static void main(String[] args) {
		Spark.port(9000);
		Spark.staticFiles.location("/templates");
		DebugScreen.enableDebugScreen();

		HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
		HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();
		
		Usuario usuarioPrueba = new Usuario("matigiorda", "123", 
				new Cliente("Matias", "Giorda", TipoDni.DNI, "123454321", "1155667788",
			"Calle verdadera 321", LocalDateTime.now(), Arrays.asList()));
		
		get("/", HomeController::mostrar, engine);	
		get("/login", LoginController::mostrar, engine);
		post("/login", LoginController::login, engine);
		get("/principal", PrincipalController::mostrar,engine);
		get("/user", UserController::mostrar, engine);
		//La idea es que sea el id en lugar del username
		get("/user/:username", UserController::mostrar, engine);
		//Tanto el hogar como el optimizador deberian saber de que usuario sacar la informacion
		get("/hogar", HogarController::mostrar, engine);
		//get("/hogar/:username", HogarController::mostrar, engine);
		get("/optimizador", OptimizadorController::mostrar, engine);
		get("/administrador", PanelAdministradorController::mostrar, engine);
		get("/administrador/hogares", PanelAdministradorController::verTodosLosHogares, engine);
		get("/consumo", ConsumoPorPeriodoController::mostrar, engine);
		//get("/consumo/:id",ConsumoPorPeriodoController::obtener, engine);
		get("/transformador",TransformadorController::mostrar, engine);
		get("/administrador/catalogo", PanelAdministradorController::catalogo, engine);
	}
}
