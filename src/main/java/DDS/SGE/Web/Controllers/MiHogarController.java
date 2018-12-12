package DDS.SGE.Web.Controllers;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

public class MiHogarController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        if (req.session().attribute(ADMIN) == "si") {
            res.redirect(ADMINISTRADOR);
            return new HomeController().mostrar(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(id));

        List<Dispositivo> dispositivosInteligente = cliente.getDispositivosInteligente();
        List<Dispositivo> dispositivosEstandar = cliente.getDispositivosEstandar();

        HashMap<String, Object> viewModel = this.armarViewModel(cliente, dispositivosInteligente, dispositivosEstandar);

        return new ModelAndView(viewModel, "mi-hogar-v2-posta.hbs");
    }

    private HashMap<String, Object> armarViewModel(Cliente cliente, List<Dispositivo> dispositivosinteligentes, List<Dispositivo> dispositivosEstandar) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("mail-icon", this.iconoNotificacionesCliente(cliente.getId()));

        if (dispositivosinteligentes.isEmpty() && dispositivosEstandar.isEmpty()) {
            viewModel.put("noHayDispositivos", "No tenés ningún dispositivo actualmente");
            viewModel.put("noHayConsumo", "No se registró consumo en el último mes");
            viewModel.put("noHayMediciones", "No se registraron ningunas mediciones recientemente");
        } else {
            viewModel.put("dispositivosInteligente", dispositivosinteligentes);
            viewModel.put("dispositivosEstandar", dispositivosEstandar);
            viewModel.put("consumoUltimoMes", "Total del último mes: " + new DecimalFormat("#0.00").format(cliente.getConsumoUltimoMes()));
            viewModel.put("consumoMesActual", "Total del mes actual: " + new DecimalFormat("#0.00").format(cliente.getConsumoMesActual()));
            viewModel.put("consumoPromedio", "Consumo promedio: " + new DecimalFormat("#0.00").format(cliente.consumoPromedioPorDispositivo()));
            viewModel.put("estimadoDiario", "Consumo diario estimado: " + new DecimalFormat("#0.00").format(cliente.consumoTotalEstimadoDiario()));
        }

        return viewModel;
    }
}
