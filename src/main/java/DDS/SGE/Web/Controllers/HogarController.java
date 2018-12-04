package DDS.SGE.Web.Controllers;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

public class HogarController extends Controller {
    //TODO: cambiar las rutas con nombres finales
    private static String ruta = "mi-hogar-v2-posta.hbs";

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
        }

        if (req.session().attribute(ADMIN)) {
            res.redirect(ADMINISTRADOR);
            return HomeController.mostrar(req, res);
        }

        String id = req.session().attribute(SESSION_NAME);
        Cliente cliente = RepositorioClientes.findByID(Long.parseLong(id));

        HashMap<String, Object> viewModel = rellenarCliente(cliente);

        List<Dispositivo> dispositivosInteligentes = cliente.getDispositivosInteligente();
        List<Dispositivo> dispositivosEstandar = cliente.getDispositivosEstandar();

        /*dispositivosInteligentes.forEach(d -> System.out.println(d.getNombre()));
        dispositivosEstandar.forEach(d -> System.out.println(d.getNombre()));*/

        viewModel.put("dispositivosInteligente", dispositivosInteligentes);
        viewModel.put("dispositivosEstandar", dispositivosEstandar);
        viewModel.put("consumoUltimoMes", cliente.getConsumoUltimoMes());
        viewModel.put("consumoMesActual", cliente.getConsumoMesActual());
        viewModel.put("consumoPromedio", cliente.consumoPromedioPorDispositivo());
        viewModel.put("estimadoDiario", cliente.consumoTotalEstimadoDiario());
        viewModel.put("usoOptimo", cliente.consultarUsoOptimo());

        return new ModelAndView(viewModel, ruta);
    }
}
