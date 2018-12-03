package DDS.SGE.Web.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import DDS.SGE.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.*;

public class ConsumoPorPeriodoController extends Controller {

    public static ModelAndView obtener(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDateTime fechaInicio = LocalDateTime.parse(req.queryParams("fechaInicio"), formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(req.queryParams("fechaFin"), formatter);

        Cliente cliente = RepositorioClientes.findByID(req.session().attribute(SESSION_NAME));

        double consumo = cliente.consumoTotalEnUnPeriodo(fechaInicio, fechaFin);

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("consumo", consumo);

        return new ModelAndView(viewModel, "home.hbs");
    }

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return HomeController.mostrar(req, res);
        }

        return new ModelAndView(null, "consumo.hbs");
    }
}
