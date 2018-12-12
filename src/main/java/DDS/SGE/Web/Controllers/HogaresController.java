package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.DecimalFormat;
import java.util.HashMap;

public class HogaresController extends Controller {
    public ModelAndView verTodosLosHogares(Request req, Response res) {
        if (req.session().attribute(ADMIN) == "no") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("clientes", RepositorioClientes.getInstance().getAllClients());
        viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(Long.parseLong(req.session().attribute(SESSION_NAME))));

        return new ModelAndView(viewModel, "hogares.hbs");
    }

    public ModelAndView hogarDe(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        try {
            String id = req.params(":id");
            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("mail-icon", this.iconoNotificacionesCliente(cliente.getId()));

            if (cliente.getDispositivosEstandar().isEmpty() && cliente.getDispositivosInteligente().isEmpty()) {
                viewModel.put("noHayDispositivos", "No tenés ningún dispositivo actualmente");
                viewModel.put("noHayConsumo", "No se registró consumo en el último mes");
                viewModel.put("noHayMediciones", "No se registraron ningunas mediciones recientemente");
            } else {
                viewModel.put("consumoUltimoMes", "Total del último mes: " + new DecimalFormat("#0.00").format(cliente.getConsumoUltimoMes()));
                viewModel.put("consumoMesActual", "Total del mes actual: " + new DecimalFormat("#0.00").format(cliente.getConsumoMesActual()));
                viewModel.put("consumoPromedio", "Consumo promedio: " + new DecimalFormat("#0.00").format(cliente.consumoPromedioPorDispositivo()));
                viewModel.put("estimadoDiario", "Consumo diario estimado: " + new DecimalFormat("#0.00").format(cliente.consumoTotalEstimadoDiario()));
            }

            viewModel.put("cliente", cliente);

            return new ModelAndView(viewModel, "hogar-de.hbs");
        } catch (Exception ex) {
            return new ErrorController().somethingBroke(req, res);
        }
    }
}
