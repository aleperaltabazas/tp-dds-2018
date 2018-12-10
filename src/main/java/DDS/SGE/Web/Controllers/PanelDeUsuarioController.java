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
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        String pantalla;

        if (req.session().attribute(ADMIN) == "si") {
            String id = req.session().attribute(SESSION_NAME);
            Administrador admin = RepositorioAdministradores.getInstance().findByID(Long.parseLong(id));

            viewModel.put("nombre", admin.getNombre());
            viewModel.put("apellido", admin.getApellido());
            viewModel.put("direccion", admin.getDomicilio());
            viewModel.put("username", admin.getUsername());
            viewModel.put("fechaDeAlta", admin.getFechaAltaSistema().toString());
            viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(admin.getId()));

            pantalla = "administrador-profile.hbs";
        } else {
            String id = req.session().attribute(SESSION_NAME);
            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

            viewModel = rellenarCliente(cliente);
            viewModel.put("mail-icon", this.iconoNotificacionesCliente(cliente.getId()));
            pantalla = "panelDeUsuario.hbs";
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public ModelAndView editar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = rellenarCliente(cliente);
        viewModel.put("mail-icon", this.iconoNotificacionesCliente(cliente.getId()));

        return new ModelAndView(viewModel, "editarUsuario.hbs");
    }

    public ModelAndView actualizar(Request req, Response res) {
        String nombre = req.queryParams("nombre");
        String apellido = req.queryParams("apellido");
        String telefono = req.queryParams("telefono");
        String direccion = req.queryParams("direccion");
        String numeroDni = req.queryParams("numeroDni");

        if (nombre == null || apellido == null || telefono == null || direccion == null || numeroDni == null) {
            return llenarTodosLosCampos(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setDomicilio(direccion);
        cliente.setNumeroDni(numeroDni);

        try {
            withTransaction(() -> RepositorioClientes.getInstance().actualizarCliente(cliente));
        } catch (Exception e) {
            return new ErrorController().notFound(req, res);
        }

        res.redirect(USER);

        return mostrar(req, res);
    }

    private ModelAndView llenarTodosLosCampos(Request req, Response res) {
        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = rellenarCliente(cliente);
        viewModel.put("mail-icon", this.iconoNotificacionesCliente(cliente.getId()));

        return new ModelAndView(viewModel, "editarUsuario_rellenar.hbs");
    }

}
