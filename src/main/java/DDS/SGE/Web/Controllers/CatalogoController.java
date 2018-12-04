package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoBuilder;
import DDS.SGE.Dispositivo.TablaDispositivos;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import Fabricante.Fabricante;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

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
        try {
            String nombre = req.queryParams("nombre");
            System.out.println(nombre);
            System.out.println(req.queryParams("consumo"));
            System.out.println(req.queryParams("fabricante"));
            System.out.println(req.queryParams("bajoConsumo"));

            double consumo = Double.parseDouble(req.queryParams("consumo"));
            Fabricante fabricante = Fabricante.parse(req.queryParams("fabricante"));
            boolean bajoConsumo;

            if (req.queryParams("bajoConsumo") == "Sí") {
                bajoConsumo = true;
            } else {
                bajoConsumo = false;
            }

            DispositivoBuilder db = new DispositivoBuilder();
            Dispositivo dispositivo = db.construirInteligente(nombre, consumo, fabricante, bajoConsumo);
            RepositorioDispositivos.agregarDispositivoAlCatalogo(dispositivo);

            res.redirect(ADMINISTRADOR);
            return PanelAdministradorController.mostrar(req, res);
        } catch (RuntimeException e) {
            e.printStackTrace();

            return new ModelAndView(null, "crear-inteligente-completar.hbs");
        }

    }

    public static ModelAndView nuevoEstandar(Request req, Response res) {
        //TODO: la lógica de persistencia
        res.redirect(ADMINISTRADOR);
        return PanelAdministradorController.mostrar(req, res);
    }
}
