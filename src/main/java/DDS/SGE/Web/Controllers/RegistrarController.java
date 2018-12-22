package DDS.SGE.Web.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import DDS.SGE.Exceptions.UserUnavailableException;
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
    private static final String ERROR = "error";
    private static final String CLIENTE = "cliente";

    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();
        if (req.session().attribute(ERROR) != null) {
            Cliente cliente = (Cliente) req.session().attribute(CLIENTE);

            viewModel.put("nombre", cliente.getNombre());
            viewModel.put("apellido", cliente.getApellido());
            viewModel.put("domicilio", cliente.getDomicilio());
            viewModel.put("numeroDni", cliente.getNumeroDni());
            viewModel.put("telefono", cliente.getTelefono());
            viewModel.put("username", cliente.getUsername());

            viewModel.put("error", true);
        }

        return new ModelAndView(viewModel, "registro.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {

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
        Cliente cliente = cb.crearCliente(nombre, apellido, numeroDni, codigoArea + telefono, username, password);

        if (RepositorioClientes.getInstance().findByUsername(username).isPresent()) {
            throw new UserUnavailableException(cliente);
        }

        List<Transformador> transformadores = RepositorioTransformadores.getInstance().listar();

        //Transformador transformador = Transformador.parse(direccion)
        //SE SE Segui soñando pelotudo

        Transformador transformador = transformadores.get(new Random().nextInt(transformadores.size()));
        transformador.agregarCliente(cliente);

        withTransaction(() -> {
            RepositorioTransformadores.getInstance().saveOrUpdate(transformador);
            //RepositorioClientes.getInstance().registrarCliente(cliente);
        });

        res.redirect(LOGIN);
        return new LoginClienteController().mostrar(req, res);
    }

    public ModelAndView userUnavailabe(Exception ex) {
        HashMap<String, Object> viewModel = this.fillError(ex);

        return new ModelAndView(viewModel, "registro.hbs");
    }
}
