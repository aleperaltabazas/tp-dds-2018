package DDS.SGE.Web.Controllers;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoBuilder;
import DDS.SGE.Dispositivo.DispositivoDeCatalogo;
import DDS.SGE.Dispositivo.MetodoDeCreacion;
import DDS.SGE.Repositorios.RepositorioCatalogo;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import DDS.SGE.Fabricante.Fabricante;
import com.github.jknack.handlebars.Handlebars;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

public class CatalogoController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        HashMap<String, Object> viewModel = new HashMap<>();

        List<DispositivoDeCatalogo> dispos = RepositorioCatalogo.getInstance().listar();
        viewModel.put("dispositivos", dispos);

        return new ModelAndView(viewModel, "catalogo.hbs");
    }

    public ModelAndView mostrarAdquirir(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        return new ModelAndView(null, "dispositivos-mostrarAdquirir.hbs");
    }

    public ModelAndView mostrarFormularioInteligente(Request req, Response res) {
        if ((req.session().attribute(ADMIN) != "si") || req.session().attribute(SESSION_NAME) == null) {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        return new ModelAndView(null, "crear-inteligente.hbs");
    }

    public ModelAndView mostrarFormularioEstandar(Request req, Response res) {
        if ((req.session().attribute(ADMIN) != "si") || req.session().attribute(SESSION_NAME) == null) {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        return new ModelAndView(null, "crear-estandar.hbs");
    }

    public ModelAndView nuevoInteligente(Request req, Response res) {
        if ((req.session().attribute(ADMIN) != "si") || req.session().attribute(SESSION_NAME) == null) {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        try {
            String nombre = req.queryParams("nombre");

            double consumo = Double.parseDouble(req.queryParams("consumo"));
            MetodoDeCreacion metodoDeCreacion = MetodoDeCreacion.parse(req.queryParams("fabricante"));
            boolean bajoConsumo;

            bajoConsumo = req.queryParams("bajoConsumo").equals("Sí");

            DispositivoBuilder db = new DispositivoBuilder();
            DispositivoDeCatalogo dispositivo = db.construirDispositivoDeCatalogo(nombre, consumo, bajoConsumo, true, metodoDeCreacion);
            withTransaction(() -> RepositorioCatalogo.getInstance().agregarDispositivoAlCatalogo(dispositivo));

            res.redirect(DISPOSITIVOS);
            return new CatalogoController().mostrar(req, res);
        } catch (RuntimeException e) {
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("error", "<div> <p class=\"error\">{{errorMessage}}</p> </div>");
            viewModel.put("errorMessage", e.getMessage());

            return new ModelAndView(viewModel, "crear-inteligente.hbs");
        }

    }

    public ModelAndView nuevoEstandar(Request req, Response res) {
        if ((req.session().attribute(ADMIN) != "si") || req.session().attribute(SESSION_NAME) == null) {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        try {
            String nombre = req.queryParams("nombre");
            MetodoDeCreacion metodoDeCreacion = MetodoDeCreacion.parse(nombre);
            double consumo = Double.parseDouble(req.queryParams("consumo"));
            long usoEstimadoDiario = Long.parseLong(req.queryParams("uso"));
            boolean bajoConsumo;

            bajoConsumo = req.queryParams("bajoConsumo").equals("Sí");

            DispositivoBuilder db = new DispositivoBuilder();
            DispositivoDeCatalogo dispositivo = db.construirDispositivoDeCatalogo(nombre, consumo, bajoConsumo, false, metodoDeCreacion);

            withTransaction(() -> RepositorioCatalogo.getInstance().agregarDispositivoAlCatalogo(dispositivo));

            res.redirect(DISPOSITIVOS);
            return new PanelDeAdministradorController().mostrar(req, res);
        } catch (RuntimeException e) {
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("error", "<div> <p class=\"error\">{{errorMessage}}</p> </div>");
            viewModel.put("errorMessage", e.getMessage());

            return new ModelAndView(viewModel, "crear-estandar.hbs");
        }
    }
}
