package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.NoSuchElementException;

import DDS.SGE.Cliente;
import DDS.SGE.ClienteBuilder;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class RegistrarController {
	public static ModelAndView mostrar(Request req, Response res) {
		return new ModelAndView(null, "registro.hbs");
	}

	public static ModelAndView registrar(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		if (!RepositorioClientes.findByUsername(username).isPresent()) {
			return usernameNoDisponible(req, res);
		}

		try {
			ClienteBuilder cb = new ClienteBuilder();
			Cliente cliente = cb.crearCliente(username, password);
			RepositorioClientes.registrarCliente(cliente);
		} catch (Exception ex) {
			// TODO: devolver pantalla de que complete todos los campos
			return llenarTodosLosCampos(req, res);
		}

		res.redirect("/login");
		return new ModelAndView(null, "registro.hbs");

	}

	private static ModelAndView usernameNoDisponible(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("username", username);
		viewModel.put("password", password);

		return new ModelAndView(viewModel, "registro_usernameNoDisponible");
	}

	private static ModelAndView llenarTodosLosCampos(Request req, Response res) {
		return new ModelAndView(null, "registro_LlenarCampos.hbs");
	}
}
