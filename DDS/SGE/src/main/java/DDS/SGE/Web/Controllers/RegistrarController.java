package DDS.SGE.Web.Controllers;

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
		String nombre = req.queryParams("nombre");
		String apellido = req.queryParams("apellido");
		String password = req.queryParams("password");
		String telefono = req.queryParams("telefono");
		String numeroDni = req.queryParams("numerodni");

		Cliente cliente;

		try {
			RepositorioClientes.findByUsername(username).get();
			// TODO: devolver pantalla de que el nombre no se encuentra disponible
			return null;
		} catch (Exception e) {
			ClienteBuilder cb = new ClienteBuilder();
			try {
				cliente = cb.crearCliente(nombre, apellido, telefono, numeroDni, username, password);
				RepositorioClientes.registrarCliente(cliente);
			} catch (Exception ex) {
				// TODO: devolver pantalla de que complete todos los campos
				return null;
			}

			res.redirect("/");
			return new ModelAndView(null, "registro.hbs");
		}

	}
}
