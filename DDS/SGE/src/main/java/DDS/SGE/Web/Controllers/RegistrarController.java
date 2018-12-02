package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.Optional;

import DDS.SGE.Cliente;
import DDS.SGE.ClienteBuilder;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Web.HashProvider;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RegistrarController {
	public static ModelAndView mostrar(Request req, Response res) {
		return new ModelAndView(null, "registro.hbs");
	}

	public static ModelAndView registrar(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");

		if (RepositorioClientes.findByUsername(username).isPresent()) {
			return usernameNoDisponible(req, res);
		}

		String nombre = req.queryParams("nombre");
		String apellido = req.queryParams("apellido");
		String codigoArea = req.queryParams("codigoTelefono");
		String telefono = req.queryParams("telefono");
		String tipoDni = req.queryParams("tipoDni");
		String numeroDni = req.queryParams("numeroDni");
		String direccion = req.queryParams("direccion");

		ClienteBuilder cb = new ClienteBuilder();
		cb.especificarTipoDocumento(tipoDni);
		cb.especificarDireccion(direccion);

		try {
			Cliente cliente = cb.crearCliente(nombre, apellido, numeroDni, codigoArea + telefono, username, password);
			RepositorioClientes.registrarCliente(cliente);

			System.out.println(username);
			System.out.println(HashProvider.hash(password));
		} catch (Exception ex) {
			// TODO: devolver pantalla de que complete todos los campos
			return usernameNoDisponible(req, res);
		}

		res.redirect("/login");
		return new ModelAndView(null, "registro.hbs");

	}

	private static ModelAndView usernameNoDisponible(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("username", req.queryParams("username"));
		viewModel.put("password", req.queryParams("password"));
		viewModel.put("nombre", req.queryParams("nombre"));
		viewModel.put("apellido", req.queryParams("apellido"));
		viewModel.put("codigoTelefono", req.queryParams("codigoTelefono"));
		viewModel.put("telefono", req.queryParams("telefono"));
		viewModel.put("numeroDni", req.queryParams("numeroDni"));
		viewModel.put("direccion", req.queryParams("direccion"));

		return new ModelAndView(viewModel, "registro_userNoDisponible.hbs");
	}
}