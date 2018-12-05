package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoBuilder;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import Fabricante.Fabricante;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

public class CatalogoController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();

        List<Dispositivo> dispos = RepositorioDispositivos.getInstance().catalogoDeDispositivos();
        viewModel.put("dispositivos", dispos);

        return new ModelAndView(viewModel, "catalogo.hbs");
    }

    public ModelAndView mostrarAdquirir(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        return new ModelAndView(null, "dispositivos-mostrarAdquirir.hbs");
    }

    public ModelAndView mostrarFormularioInteligente(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        if (req.session().attribute(ADMIN) != "si") {
            return new ModelAndView(null, "404.hbs");
        }

        return new ModelAndView(null, "crear-inteligente.hbs");
    }

    public ModelAndView mostrarFormularioEstandar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        if (req.session().attribute(ADMIN) != "si") {
            return new ModelAndView(null, "404.hbs");
        }

        return new ModelAndView(null, "crear-estandar.hbs");
    }

    public ModelAndView nuevoInteligente(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        if (req.session().attribute(ADMIN) != "si") {
            return new ModelAndView(null, "404.hbs");
        }

        try {
            String nombre = req.queryParams("nombre");

            double consumo = Double.parseDouble(req.queryParams("consumo"));
            Fabricante fabricante = Fabricante.parse(req.queryParams("fabricante"));
            boolean bajoConsumo;

            bajoConsumo = req.queryParams("bajoConsumo").equals("Sí");

            DispositivoBuilder db = new DispositivoBuilder();
            Dispositivo dispositivo = db.construirInteligente(nombre, consumo, fabricante, bajoConsumo);
            withTransaction(() -> RepositorioDispositivos.getInstance().agregarDispositivoAlCatalogo(dispositivo));

            res.redirect(DISPOSITIVOS);
            return new CatalogoController().mostrar(req, res);
        } catch (RuntimeException e) {
            return new ModelAndView(null, "crear-inteligente-completar.hbs");
        }

    }

    public ModelAndView nuevoEstandar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(HOME);
            return new HomeController().mostrar(req, res);
        }

        if (req.session().attribute(ADMIN) != "si") {
            return new ModelAndView(null, "404.hbs");
        }

        try {
            String nombre = req.queryParams("nombre");
            double consumo = Double.parseDouble(req.queryParams("consumo"));
            long usoEstimadoDiario = Long.parseLong(req.queryParams("uso"));
            boolean bajoConsumo;

            bajoConsumo = req.queryParams("bajoConsumo").equals("Sí");

            DispositivoBuilder db = new DispositivoBuilder();
            Dispositivo dispositivo = db.construirEstandar(nombre, consumo, usoEstimadoDiario, bajoConsumo);

            withTransaction(() -> RepositorioDispositivos.getInstance().agregarDispositivoAlCatalogo(dispositivo));

            res.redirect(DISPOSITIVOS);
            return new PanelDeAdministradorController().mostrar(req, res);
        } catch (RuntimeException e) {
            return new ModelAndView(null, "crear-estandar-completar.hbs");
        }

    }
}
