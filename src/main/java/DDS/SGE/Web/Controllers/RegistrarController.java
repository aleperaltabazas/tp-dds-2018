package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import DDS.SGE.Geoposicionamiento.Transformador;
import DDS.SGE.Repositorios.RepositorioTransformadores;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Usuarie.ClienteBuilder;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.*;

public class RegistrarController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        return new ModelAndView(null, "registro.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) != null) {
            res.redirect(HOME);
            return new HomeController().home(req, res);
        }

        String username = req.queryParams("username");
        String password = req.queryParams("password");

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
            List<Transformador> transformadores = RepositorioTransformadores.getInstance().listar();

            //Transformador transformador = Transformador.parse(direccion)
            //SE SE Segui soñando pelotudo

            Transformador transformador = transformadores.get(new Random().nextInt(transformadores.size()));

            transformador.agregarCliente(cliente);

            withTransaction(() -> {
                RepositorioClientes.getInstance().registrarCliente(cliente);
                RepositorioTransformadores.getInstance().saveOrUpdate(transformador);
            });


        } catch (RuntimeException ex) {
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("username", req.queryParams("username"));
            viewModel.put("password", req.queryParams("password"));
            viewModel.put("nombre", req.queryParams("nombre"));
            viewModel.put("apellido", req.queryParams("apellido"));
            viewModel.put("codigoTelefono", req.queryParams("codigoTelefono"));
            viewModel.put("telefono", req.queryParams("telefono"));
            viewModel.put("numeroDni", req.queryParams("numeroDni"));
            viewModel.put("direccion", req.queryParams("direccion"));
            viewModel.put("errorMessage", ex.getMessage());

            return new ModelAndView(viewModel, "registro.hbs");
        }

        res.redirect(LOGIN);
        return new ModelAndView(null, "registro.hbs");

    }
}
