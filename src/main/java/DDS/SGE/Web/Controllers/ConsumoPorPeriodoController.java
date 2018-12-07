package DDS.SGE.Web.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.*;

public class ConsumoPorPeriodoController extends Controller {
    private static final String ERROR = "ERROR";

    public static ModelAndView obtener(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            String inicio = req.queryParams("fechaInicio");
            String fin = req.queryParams("fechaFin");

            //TODO - Aparentemente se debe poner hora también, por default puse 00:00
            LocalDateTime fechaInicio = LocalDate.parse(inicio, formatter).atStartOfDay();
            LocalDateTime fechaFin = LocalDate.parse(fin, formatter).atStartOfDay();

            if (fechaInicio.isAfter(fechaFin)) {
                throw new NullPointerException("La fecha fin es después de la fecha inicio");
            }

            System.out.println(Long.valueOf(req.session().attribute(SESSION_NAME)));

            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.valueOf(req.session().attribute(SESSION_NAME)));

            double consumo = cliente.consumoTotalEnUnPeriodo(fechaInicio, fechaFin);

            String periodo = fechaInicio.format(formatter) + " - " + fechaFin.format(formatter);

            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("fechaInicio", fechaInicio.format(formatter));
            viewModel.put("fechaFin", fechaFin.format(formatter));
            viewModel.put("consumo", consumo);
            viewModel.put("periodo", periodo);

            return new ModelAndView(viewModel, "consumo-obtener.hbs");
        } catch (NullPointerException e) {
            e.printStackTrace();
            req.session().attribute("ERROR", true);
            res.redirect(CONSUMO);
            return new ModelAndView(null, "consumo-error-fecha.hbs");
        }
    }

    public static ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        if (req.session().attribute(ERROR) != null &&
                req.session().attribute(ERROR).equals(true)) {
            //QUE ASCO JAVA DE MIERDA
            return new ModelAndView(null, "consumo-error-fecha.hbs");
        } else {
            return new ModelAndView(null, "consumo.hbs");
        }

    }
}
