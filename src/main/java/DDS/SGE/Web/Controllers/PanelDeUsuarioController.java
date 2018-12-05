package DDS.SGE.Web.Controllers;

import java.util.HashMap;

import DDS.SGE.Administrador;
import DDS.SGE.Cliente;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Response;
import spark.Request;

import static DDS.SGE.Web.Controllers.Routes.*;

public class PanelDeUsuarioController extends Controller {
    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
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

            pantalla = "administrador-profile.hbs";
        } else {
            String id = req.session().attribute(SESSION_NAME);
            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

            viewModel = rellenarCliente(cliente);
            pantalla = "panelDeUsuario.hbs";
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public static ModelAndView editar(Request req, Response res) {
        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

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
            /*System.out.println(nombre);
            System.out.println(apellido);
            System.out.println(telefono);
            System.out.println(direccion);
            System.out.println(numeroDni);*/

            return llenarTodosLosCampos(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setDomicilio(direccion);
        cliente.setNumeroDni(numeroDni);

        RepositorioClientes.getInstance().actualizarCliente(cliente);

        res.redirect(USER);

        return mostrar(req, res);
    }

    private static ModelAndView llenarTodosLosCampos(Request req, Response res) {
        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = rellenarCliente(cliente);

        return new ModelAndView(viewModel, "editarUsuario_rellenar.hbs");
    }

}
