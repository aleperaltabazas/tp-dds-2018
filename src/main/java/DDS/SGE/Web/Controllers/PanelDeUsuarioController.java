package DDS.SGE.Web.Controllers;

import java.util.HashMap;

import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Response;
import spark.Request;

import static DDS.SGE.Web.Controllers.Routes.*;

public class PanelDeUsuarioController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();
        String pantalla;

        if (req.session().attribute(ADMIN) == "si") {
            viewModel = this.rellenarAdministrador(viewModel, req.session().attribute(SESSION_NAME));

            pantalla = "administrador-profile.hbs";
        } else {
            String id = req.session().attribute(SESSION_NAME);
            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

            viewModel = rellenarCliente(null, req.session().attribute(SESSION_NAME));

            viewModel.put("permiso", cliente.getPermiteApagar() ? "sí" : "no");
            pantalla = "panel-usuario.hbs";
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public ModelAndView editar(Request req, Response res) {
        HashMap<String, Object> viewModel = rellenarCliente(null, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "editar-perfil-user.hbs");
    }

    public ModelAndView actualizar(Request req, Response res) {
        String nombre = req.queryParams("nombre");
        String apellido = req.queryParams("apellido");
        String telefono = req.queryParams("telefono");
        String direccion = req.queryParams("direccion");
        String permiso = req.queryParams("permiso");
        boolean permiteApagar = permiso.equals("Sí");

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setDomicilio(direccion);
        cliente.setPermiteApagar(permiteApagar);

        withTransaction(() -> RepositorioClientes.getInstance().saveOrUpdate(cliente));

        res.redirect(USER_PROFILE);

        return mostrar(req, res);
    }

    public ModelAndView mostrarSettings(Request req, Response res) {
        HashMap<String, Object> viewModel = this.rellenarCliente(null, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "user-settings.hbs");
    }
}
