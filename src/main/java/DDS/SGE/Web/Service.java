package DDS.SGE.Web;

import DDS.SGE.Exceptions.AdminNotFoundException;
import DDS.SGE.Exceptions.UnauthorizedAccessException;
import DDS.SGE.Exceptions.UserNotFoundException;
import DDS.SGE.Exceptions.UserUnavailableException;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Solicitud.ChabonQueTePersisteCuandoSeCierraUnaSolicitud;
import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Utils.PersistirMain;
import DDS.SGE.Web.Controllers.*;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static DDS.SGE.Web.Controllers.Routes.*;
import static spark.Spark.*;

public class Service {
    private static final Logger LOGGER = Logger.getLogger(Service.class.getName());

    private HomeController homeController;
    private ConsumoPorPeriodoController consumoPorPeriodoController;
    private CatalogoController catalogoController;
    private ErrorController errorController;
    private MiHogarController miHogarController;
    private LoginController loginController;
    private LoginAdminController loginAdminController;
    private LoginClienteController loginClienteController;
    private OptimizadorController optimizadorController;
    private PanelDeAdministradorController panelDeAdministradorController;
    private PanelDeUsuarioController panelDeUsuarioController;
    private RegistrarController registrarController;
    private TransformadorController transformadorController;
    private SolicitudesController solicitudesController;
    private HogaresController hogaresController;

    public static void main(String[] args) {
        new Service().run();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    private void inicializarControladores() {
        homeController = new HomeController();
        consumoPorPeriodoController = new ConsumoPorPeriodoController();
        catalogoController = new CatalogoController();
        errorController = new ErrorController();
        loginController = new LoginController();
        loginAdminController = new LoginAdminController();
        loginClienteController = new LoginClienteController();
        hogaresController = new HogaresController();
        miHogarController = new MiHogarController();
        optimizadorController = new OptimizadorController();
        panelDeAdministradorController = new PanelDeAdministradorController();
        panelDeUsuarioController = new PanelDeUsuarioController();
        registrarController = new RegistrarController();
        transformadorController = new TransformadorController();
        solicitudesController = new SolicitudesController();
    }

    private boolean shouldRedirect(String[] route) {
        List<String> exceptions = Arrays.asList("login", "500", "signup", "users", "administrador/login");

        return !exceptions.stream().anyMatch(r -> r.equals(route[0]));
    }

    @SuppressWarnings("Duplicates")
    private void inicializarRutas() {
        HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
        HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

        before("/*", (request, response) -> {
            if (request.splat() == null || request.splat().length == 0) {
                homeController.home(request, response);
            } else if (request.session().attribute("id") == null && shouldRedirect(request.splat())) {
                response.redirect(LOGIN);
            }
        });

        before(SOLICITUDES, (req, res) -> {
            if (req.session().attribute("admin") == "si") {
                Administrador admin = RepositorioAdministradores.getInstance().findByID(Long.parseLong(req.session().attribute("id")));
                new ChabonQueTePersisteCuandoSeCierraUnaSolicitud().leer(admin);
            } else {
                Cliente cliente = RepositorioClientes.getInstance().findByID(Long.parseLong(req.session().attribute("id")));
                new ChabonQueTePersisteCuandoSeCierraUnaSolicitud().leer(cliente);
            }
        });

        before(SIGNUP, (req, res) -> {
            if (req.session().attribute("id") != null) {
                res.redirect(HOME);
                new HomeController().home(req, res);
            }
        });

        get(HOME, homeController::home, engine);

        get(LOGIN, loginClienteController::mostrar, engine);
        post(LOGIN, loginClienteController::loginCliente, engine);

        get(HOGAR, miHogarController::mostrar, engine);

        get(OPTIMIZADOR, optimizadorController::mostrar, engine);

        get(ADMINISTRADOR_LOGIN, loginAdminController::mostrar, engine);
        post(ADMINISTRADOR_LOGIN, loginAdminController::loginAdmin, engine);

        get(ADMINISTRADOR, panelDeAdministradorController::mostrar, engine);

        get(HOGARES, hogaresController::verTodosLosHogares, engine);
        get(HOGARES_ID, hogaresController::hogarDe, engine);

        get(DISPOSITIVOS, catalogoController::mostrar, engine);
        get(DISPOSITIVOS_PAGE, catalogoController::mostrar, engine);

        post(DISPOSITIVOS_ID_ACQUIRE, catalogoController::solicitar, engine);
        get(DISPOSITIVOS_ID_INFO, catalogoController::mostrarFichaTecnica, engine);

        get(DISPOSITIVOS_NEW_INTELIGENTE, catalogoController::mostrarFormularioInteligente, engine);
        post(DISPOSITIVOS_NEW_INTELIGENTE, catalogoController::nuevoInteligente, engine);
        get(DISPOSITIVOS_NEW_ESTANDAR, catalogoController::mostrarFormularioEstandar, engine);
        post(DISPOSITIVOS_NEW_ESTANDAR, catalogoController::nuevoEstandar, engine);

        get(CONSUMO, consumoPorPeriodoController::mostrar, engine);
        get(CONSUMO_OBTENER, consumoPorPeriodoController::obtener, engine);

        get(TRANSFORMADOR, transformadorController::mostrar, engine);
        get(TRANSFORMADOR_CONSUMO_OBTENER, transformadorController::obtenerConsumo, engine);

        get(USER_PROFILE, panelDeUsuarioController::mostrar, engine);
        get(USER_EDIT, panelDeUsuarioController::editar, engine);
        post(USER, panelDeUsuarioController::actualizar, engine);

        get(USER_SETTINGS, panelDeUsuarioController::mostrarSettings, engine);

        post(USER_DISPOSITIVOS_ID_ON, miHogarController::encender, engine);
        post(USER_DISPOSITIVOS_ID_OFF, miHogarController::apagar, engine);

        get(SOLICITUDES, solicitudesController::mostrar, engine);
        get(SOLICITUDES_ID_INFO, solicitudesController::verSolicitud, engine);
        post(SOLICITUDES_ID_ACCEPT, solicitudesController::aceptar, engine);
        post(SOLICITUDES_ID_REJECT, solicitudesController::rechazar, engine);

        get(SIGNUP, registrarController::mostrar, engine);
        post(USERS, registrarController::registrar, engine);

        get(LOGOUT, loginController::logout, engine);

        //TODO: get(LIFE, controller::fortyTwo, engine);
        get("/500", errorController::somethingBroke, engine);
        get(GLITCH, errorController::notFound, engine);

        notFound((req, res) -> errorController.notFound(req, res));
        internalServerError((req, res) -> errorController.somethingBroke(req, res));

        exception(Exception.class, (e, req, res) -> LOGGER.info(e.getMessage()));

        exception(UserUnavailableException.class, (e, req, res) -> {
            req.session().attribute("error", true);
            req.session().attribute("cliente", e.getCliente());
            res.redirect(SIGNUP);
        });

        exception(UnauthorizedAccessException.class, (e, req, res) -> {
            res.redirect("/500");
            errorController.unauthorizedAccess(req, res);
        });

        exception(UserNotFoundException.class, (e, req, res) -> {
            res.redirect(LOGIN);
            loginClienteController.loginError(e);
        });

        exception(AdminNotFoundException.class, (e, req, res) -> {
            res.redirect(LOGIN);
            loginAdminController.loginError(e);
        });
    }

    public void run() {
        this.sparkSetup();

        new PersistirMain().initialize();

        this.inicializarControladores();
        this.inicializarRutas();
    }

    public void sparkSetup() {
        //Para debuggear localhost
        Spark.port(9000);

        //Para el deploy en heroku
        //Spark.port(getHerokuAssignedPort());

        Spark.staticFiles.location("/templates");
        DebugScreen.enableDebugScreen();

    }

}
