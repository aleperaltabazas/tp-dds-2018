package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.TablaDispositivos;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class CatalogoController extends Controller {
    public static final String TIPO_DISPOSITIVO = "TIPO_DISPOSITIVO";

    public static ModelAndView mostrar(Request req, Response res) {
        HashMap<String, Object> viewModel = new HashMap<>();

        List<Dispositivo> dispos = RepositorioDispositivos.catalogoDeDispositivos();
        viewModel.put("dispositivos", dispos);

        return new ModelAndView(viewModel, "catalogo.hbs");
    }

    public static ModelAndView adquirir(Request request, Response response) {
        return new ModelAndView(null, "dispositivos-adquirir.hbs");
    }

    public static ModelAndView crearInteligente(Request request, Response response) {
        return null;
    }

    public static ModelAndView crearEstandar(Request req, Response res) {
        return null;
    }

    public static ModelAndView mostrarFormaDeCreacion(Request request, Response response) {
        return new ModelAndView(null, "dispositivos-crear,hbs");
    }

    public static ModelAndView nuevoInteligente(Request req, Response res) {
        return null;
    }

    public static ModelAndView nuevoEstandar(Request req, Response res) {
        return null;
    }
}
