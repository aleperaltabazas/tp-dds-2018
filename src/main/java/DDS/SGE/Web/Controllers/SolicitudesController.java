package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioSolicitudes;
import DDS.SGE.Solicitud.SolicitudAbierta;
import DDS.SGE.Usuarie.Administrador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static DDS.SGE.Web.Controllers.Routes.*;

public class SolicitudesController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null) {
            res.redirect(LOGIN);
            return new LoginClienteController().mostrar(req, res);
        }

        String pantalla;

        HashMap<String, Object> viewModel = new HashMap<>();

        if (req.session().attribute(ADMIN) == "si") {
            pantalla = "solicitudes-administrador.hbs";
            viewModel.put("pendientes", RepositorioSolicitudes.getInstance().listaAbiertas());
            viewModel.put("cerradas", RepositorioSolicitudes.getInstance().listarCerradasPor(Long.parseLong(req.session().attribute(SESSION_NAME))));
        } else {
            pantalla = "solicitudes.hbs";
            viewModel.put("pendientes", RepositorioSolicitudes.getInstance().solicitudesAbiertasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));
            viewModel.put("cerradas", RepositorioSolicitudes.getInstance().solicitudesCerradasDe(Long.parseLong(req.session().attribute(SESSION_NAME))));
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public ModelAndView aceptar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null || req.session().attribute(ADMIN) != "si") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        SolicitudAbierta solicitud = RepositorioSolicitudes.getInstance().findByIDAbierta(Long.parseLong(req.params(":id")));
        Administrador administrador = RepositorioAdministradores.getInstance().findByID(Long.parseLong(req.session().attribute(SESSION_NAME)));

        try {
            solicitud.aceptar(administrador);
        } catch (Exception e) {
            return new ErrorController().somethingBroke(req, res);
        }

        res.redirect(SOLICITUDES);
        return this.mostrar(req, res);
    }

    public ModelAndView rechazar(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null || req.session().attribute(ADMIN) != "si") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        SolicitudAbierta solicitud = RepositorioSolicitudes.getInstance().findByIDAbierta(Long.parseLong(req.params(":id")));
        Administrador administrador = RepositorioAdministradores.getInstance().findByID(Long.parseLong(req.session().attribute(SESSION_NAME)));

        try {
            solicitud.rechazar(administrador);
        } catch (Exception e) {
            return new ErrorController().somethingBroke(req, res);
        }

        res.redirect(SOLICITUDES);
        return this.mostrar(req, res);
    }

    public ModelAndView verSolicitud(Request req, Response res) {
        if (req.session().attribute(SESSION_NAME) == null || req.session().attribute(ADMIN) != "si") {
            return new ErrorController().unauthorizedAccess(req, res);
        }

        SolicitudAbierta solicitud = RepositorioSolicitudes.getInstance().findByIDAbierta(Long.parseLong(req.params(":id")));

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("solicitud", solicitud);

        return new ModelAndView(viewModel, "ver-solicitud.hbs");
    }
}
