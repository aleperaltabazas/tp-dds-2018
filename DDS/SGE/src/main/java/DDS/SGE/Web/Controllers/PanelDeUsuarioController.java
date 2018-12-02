package DDS.SGE.Web.Controllers;

import java.util.HashMap;

import DDS.SGE.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Response;
import spark.Request;

public class PanelDeUsuarioController {
	protected static final String SESSION_NAME = "id";

	public static ModelAndView mostrar(Request req, Response res) {
		if (req.session().attribute(SESSION_NAME) == null) {
			res.redirect("/");
			return HomeController.mostrar(req, res);
		}

		String id = req.session().attribute(SESSION_NAME);

		Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

		HashMap<String, Object> viewModel = rellenarCliente(cliente);

		return new ModelAndView(viewModel, "panelDeUsuario.hbs");
	}

	public static ModelAndView editar(Request req, Response res) {
		String id = req.session().attribute(SESSION_NAME);
		Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

		HashMap<String, Object> viewModel = rellenarCliente(cliente);

		return new ModelAndView(viewModel, "editarUsuario.hbs");
	}

	public static ModelAndView actualizar(Request req, Response res) {
		String nombre = req.queryParams("nombre");
		String apellido = req.queryParams("apellido");
		String telefono = req.queryParams("telefono");
		String direccion = req.queryParams("direccion");
		String numeroDni = req.queryParams("numeroDni");

		if (nombre == null || apellido == null || telefono == null || direccion == null || numeroDni == null) {
			System.out.println(nombre);
			System.out.println(apellido);
			System.out.println(telefono);
			System.out.println(direccion);
			System.out.println(numeroDni);

			return llenarTodosLosCampos(req, res);
		}

		String id = req.session().attribute(SESSION_NAME);
		Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setTelefono(telefono);
		cliente.setDomicilio(direccion);
		cliente.setNumeroDni(numeroDni);

		res.redirect("/me");

		return mostrar(req, res);
	}

	public static ModelAndView llenarTodosLosCampos(Request req, Response res) {
		String id = req.session().attribute(SESSION_NAME);
		Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

		HashMap<String, Object> viewModel = rellenarCliente(cliente);

		return new ModelAndView(viewModel, "editarUsuario_rellenar.hbs");
	}

	private static HashMap<String, Object> rellenarCliente(Cliente cliente) {
		HashMap<String, Object> viewModel = new HashMap<>();

		viewModel.put("nombre", cliente.getNombre());
		viewModel.put("apellido", cliente.getApellido());
		viewModel.put("telefono", cliente.getTelefono());
		viewModel.put("numeroDni", cliente.getNumeroDni());
		viewModel.put("tipoDni", cliente.getTipoDni());
		viewModel.put("domicilio", cliente.getDomicilio());
		viewModel.put("categoria", cliente.getCategoria().toString());
		viewModel.put("username", cliente.getUsername());

		return viewModel;
	}
}
