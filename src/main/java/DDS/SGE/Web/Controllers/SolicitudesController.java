package DDS.SGE.Web.Controllers;

import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioSolicitudes;
import DDS.SGE.Solicitud.SolicitudAbierta;
import DDS.SGE.Solicitud.SolicitudCerrada;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static DDS.SGE.Web.Controllers.Routes.*;

public class SolicitudesController extends Controller {
    public ModelAndView mostrar(Request req, Response res) {
        String pantalla;

        HashMap<String, Object> viewModel = new HashMap<>();

        if (req.session().attribute(ADMIN) == "si") {
            Long id = Long.parseLong(req.session().attribute(SESSION_NAME));

            pantalla = "solicitudes-administrador.hbs";

            viewModel.put("pendientes", RepositorioSolicitudes.getInstance().listarAbiertas());
            viewModel.put("cerradas", RepositorioSolicitudes.getInstance().listarCerradasPor(id));
            viewModel.put("mail-icon", this.iconoNotificacionesAdministrador(id));

            Administrador administrador = RepositorioAdministradores.getInstance().findByID(id);
            administrador.setTieneNotificaciones(false);

            withTransaction(() -> RepositorioAdministradores.getInstance().saveOrUpdate(administrador));
        } else {
            Long id = Long.parseLong(req.session().attribute(SESSION_NAME));

            List<SolicitudAbierta> solicitudesAbiertas = RepositorioSolicitudes.getInstance().solicitudesAbiertasDe(Long.parseLong(req.session().attribute(SESSION_NAME)));
            List<SolicitudCerrada> solicitudesCerradas = RepositorioSolicitudes.getInstance().solicitudesCerradasDe(Long.parseLong(req.session().attribute(SESSION_NAME)));

            pantalla = "solicitudes-user.hbs";

            viewModel.put("pendientes", solicitudesAbiertas);
            viewModel.put("cerradas", solicitudesCerradas);
            viewModel.put("mail-icon", this.iconoNotificacionesCliente(id));

            Cliente cliente = RepositorioClientes.getInstance().findByID(id);
            cliente.setTieneNotificaciones(false);

            withTransaction(() -> RepositorioClientes.getInstance().saveOrUpdate(cliente));
        }

        return new ModelAndView(viewModel, pantalla);
    }

    public ModelAndView aceptar(Request req, Response res) {
        if (req.session().attribute(ADMIN) != "si") {
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
        viewModel.put("mail-icon", this.iconoNotificacionesCliente(Long.parseLong(req.session().attribute(SESSION_NAME))));

        return new ModelAndView(viewModel, "ver-solicitud.hbs");
    }
}
