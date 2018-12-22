package DDS.SGE.Web.Controllers;

import DDS.SGE.Exceptions.UnauthorizedAccessException;
import DDS.SGE.Geoposicionamiento.Transformador;
import DDS.SGE.Repositorios.RepositorioTransformadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TransformadorController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("transformadores", RepositorioTransformadores.getInstance().listar());

        return new ModelAndView(viewModel, "consumo-transformador.hbs");
    }

    public ModelAndView obtenerConsumo(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
            throw new UnauthorizedAccessException(Long.parseLong(req.session().attribute(SESSION_NAME)), req);
        }

        String trafoId = req.queryParams("transformador");
        System.out.println(trafoId + "***");
        Transformador transformador = RepositorioTransformadores.getInstance().findByID(Long.parseLong(trafoId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String inicio = req.queryParams("fechaInicio");
        String fin = req.queryParams("fechaFin");

        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
        LocalDate fechaFin = LocalDate.parse(fin, formatter);

        RepositorioTransformadores.getInstance().listar().forEach(t -> logInfo(t.getId().toString()));

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("consumo", transformador.consumoEnElPeriodo(fechaInicio.atStartOfDay(), fechaFin.atStartOfDay()));
        viewModel.put("transformadores", RepositorioTransformadores.getInstance().listar());
        viewModel = this.rellenarAdministrador(viewModel, req.session().attribute(SESSION_NAME));

        return new ModelAndView(viewModel, "consumo-transformador-obtener.hbs");
    }
}
