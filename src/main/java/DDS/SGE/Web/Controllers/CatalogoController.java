package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.TablaDispositivos;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.ADMINISTRADOR;

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

    public static ModelAndView mostrarInteligente(Request request, Response response) {
        return new ModelAndView(null, "crear-inteligente.hbs");
    }

    public static ModelAndView mostrarEstandar(Request req, Response res) {
        return new ModelAndView(null, "crear-estandar.hbs");
    }

    public static ModelAndView nuevoInteligente(Request req, Response res) {
        //TODO: la lógica de persistencia
        res.redirect(ADMINISTRADOR);
        return PanelAdministradorController.mostrar(req, res);
    }

    public static ModelAndView nuevoEstandar(Request req, Response res) {
        //TODO: la lógica de persistencia
        res.redirect(ADMINISTRADOR);
        return PanelAdministradorController.mostrar(req, res);
    }
}
