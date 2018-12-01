package DDS.SGE.Web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.Arrays;

import DDS.SGE.*;
import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Web.Controllers.*;
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

		Cliente cliente = new Cliente("Matias", "Giorda", TipoDni.DNI, "123454321", "1155667788", "Calle verdadera 321",
				LocalDateTime.now(), Arrays.asList());
		RepositorioClientes.agregarCliente(cliente);

		Cliente c1 = new Cliente("Alesaurio", HashProvider.hash("pass"));
		RepositorioClientes.agregarCliente(c1);

		get("/", HomeController::mostrar, engine);
		get("/login", LoginClienteController::mostrar, engine);
		post("/login", LoginClienteController::loginCliente, engine);
		get("/administrador/login", LoginAdminController::loginAdmin, engine);
		get("/principal", PrincipalController::mostrar, engine);
		get("/user", UserController::mostrar, engine);
		get("/user/:id", UserController::mostrar, engine);
		// Tanto el hogar como el optimizador deberian saber de que usuario sacar la
		// informacion
		get("/hogar", HogarController::mostrar, engine);
		// get("/hogar/:username", HogarController::mostrar, engine);
		get("/optimizador", OptimizadorController::mostrar, engine);
		get("/administrador", PanelAdministradorController::mostrar, engine);
		get("/administrador/new", PanelAdministradorController::nuevoDispositivo, engine);
		get("/administrador/hogares", PanelAdministradorController::verTodosLosHogares, engine);
		get("/consumo", ConsumoPorPeriodoController::mostrar, engine);
		// get("/consumo/:id",ConsumoPorPeriodoController::obtener, engine);
		get("/transformador", TransformadorController::mostrar, engine);
		get("/me", PanelDeUsuarioController::mostrar, engine);

	}
}
