package DDS.SGE.Web.Controllers;

import DDS.SGE.Exceptions.UnauthorizedAccessException;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class PanelDeAdministradorController extends Controller {

    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        HashMap<String, Object> viewModel = this.rellenarAdministrador(null, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "panel-administrador.hbs");
    }

    public ModelAndView verConsumoPromedioCliente(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        String id = req.params("id");
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = this.rellenarAdministrador(null, req.session().attribute(SESSION_NAME));
        viewModel.put("tuplas", cliente.consumoPromedioDispositivoPosta());
        viewModel.put("estimadoDiario", cliente.consumoTotalEstimadoDiario());
        viewModel.put("promedio", cliente.consumoPromedioPorDispositivo());

        return new ModelAndView(viewModel, "consumo-promedio.hbs");
    }

    public ModelAndView verTodosLosClientes(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        String page = req.params(":page");
        int pageNumber;

        if (page == null || Integer.parseInt(page) == 1) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(page);
        }

        List<Cliente> clientes = RepositorioClientes.getInstance().getAllClients();

        HashMap<String, Object> viewModel = this.rellenarAdministrador(null, req.session().attribute(SESSION_NAME));
        viewModel.put("clientes", clientes);

        viewModel.put("previousPage", pageNumber - 1);
        viewModel.put("hayAnterior", pageNumber > 1);
        viewModel.put("nextPage", pageNumber + 1);
        viewModel.put("haySiguiente", pageNumber < RepositorioClientes.getInstance().cantidadDePaginas());

        return new ModelAndView(viewModel, "users.hbs");
    }
}