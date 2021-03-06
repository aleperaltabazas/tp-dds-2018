package DDS.SGE.Web.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Logger;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Utils.PersistirMain;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static DDS.SGE.Web.Controllers.Routes.CONSUMO;


public class ConsumoPorPeriodoController extends Controller {
    private static final String ERROR = "ERROR";

    public ModelAndView obtener(Request req, Response res) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String inicio = req.queryParams("fechaInicio");
            String fin = req.queryParams("fechaFin");

            //TODO - Aparentemente se debe poner hora también, por default puse 00:00
            LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
            LocalDate fechaFin = LocalDate.parse(fin, formatter);
            String mensaje;

            if (fechaInicio.isAfter(fechaFin)) {
            	mensaje = "La fecha de fin debe ser posterior a la fecha de inicio";
                throw new RuntimeException("La fecha fin es después de la fecha inicio");
            }

            Cliente cliente = RepositorioClientes.getInstance().findByID(Long.valueOf(req.session().attribute(SESSION_NAME)));

            double consumo = cliente.consumoTotalEnUnPeriodo(fechaInicio.atStartOfDay(), fechaFin.atStartOfDay());

            String periodo = fechaInicio.format(formatter) + " - " + fechaFin.format(formatter) + ":";
            
            if(consumo != 0) mensaje = "Consumo total en el período";
            else mensaje = "No tiene consumo registrado en el período";

            HashMap<String, Object> viewModel = this.rellenarCliente(null, req.session().attribute(SESSION_NAME));
            viewModel.put("fechaInicio", fechaInicio.format(formatter));
            viewModel.put("fechaFin", fechaFin.format(formatter));
            viewModel.put("mensaje", mensaje);
            viewModel.put("consumo", consumo);
            viewModel.put("periodo", periodo);
            return new ModelAndView(viewModel, "consumo-obtener.hbs");
        } catch (RuntimeException e) {
            req.session().attribute("ERROR", true);
            res.redirect(CONSUMO);

            HashMap<String, Object> viewModel = this.fillError(e);
            viewModel = this.rellenarCliente(viewModel, req.session().attribute(SESSION_NAME));

            return new ModelAndView(viewModel, "consumo-obtener.hbs");
        }
    }

    public ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = this.rellenarCliente(null, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "consumo-obtener.hbs");
    }
}
