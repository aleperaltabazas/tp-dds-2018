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
			return HomeController.mostrar(req, res);
		}

		String id = req.session().attribute(SESSION_NAME);

		Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("nombre", cliente.getNombre());
		viewModel.put("apellido", cliente.getApellido());
		viewModel.put("telefono", cliente.getTelefono());
		viewModel.put("numeroDni", cliente.getNumeroDni());
		
		return new ModelAndView(viewModel, "panelDeUsuario.hbs");
	}
}
