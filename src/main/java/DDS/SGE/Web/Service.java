package DDS.SGE.Web;

import static DDS.SGE.Web.Controllers.Routes.*;
import static spark.Spark.get;
import static spark.Spark.post;

import DDS.SGE.*;
import DDS.SGE.Web.Controllers.*;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
    private HomeController homeController;
    private CatalogoController catalogoController;
    private ErrorController errorController;
    private MiHogarController miHogarController;
    private LoginAdminController loginAdminController;
    private LoginClienteController loginClienteController;
    private OptimizadorController optimizadorController;
    private PanelDeAdministradorController panelDeAdministradorController;
    private PanelDeUsuarioController panelDeUsuarioController;
    private RegistrarController registrarController;
    private TransformadorController transformadorController;

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

    private void inicialiarControladores() {
        homeController = new HomeController();
        catalogoController = new CatalogoController();
        errorController = new ErrorController();
        loginAdminController = new LoginAdminController();
        loginClienteController = new LoginClienteController();
        miHogarController = new MiHogarController();
        optimizadorController = new OptimizadorController();
        panelDeAdministradorController = new PanelDeAdministradorController();
        panelDeUsuarioController = new PanelDeUsuarioController();
        registrarController = new RegistrarController();
        transformadorController = new TransformadorController();
    }

    private void router() {
        HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
        HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

        get(HOME, HomeController::mostrar, engine);

        get(LOGIN, LoginClienteController::mostrar, engine);
        post(LOGIN, LoginClienteController::loginCliente, engine);

        get(HOGAR, MiHogarController::mostrar, engine);

        get(OPTIMIZADOR, OptimizadorController::mostrar, engine);

        get(ADMINISTRADOR_LOGIN, LoginAdminController::mostrar, engine);
        post(ADMINISTRADOR_LOGIN, LoginAdminController::loginAdmin, engine);

        get(ADMINISTRADOR, PanelDeAdministradorController::mostrar, engine);
        get(ADMINISTRADOR_HOGARES, PanelDeAdministradorController::verTodosLosHogares, engine);

        get(DISPOSITIVOS, CatalogoController::mostrar, engine);
        get(DISPOSITIVOS_ACQUIRE, CatalogoController::adquirir);
        get(DISPOSITIVOS_NEW_INTELIGENTE, CatalogoController::mostrarInteligente, engine);
        get(DISPOSITIVOS_NEW_ESTANDAR, CatalogoController::mostrarEstandar, engine);
        post(DISPOSITIVOS_NEW_INTELIGENTE, CatalogoController::nuevoInteligente, engine);
        post(DISPOSITIVOS_NEW_ESTANDAR, CatalogoController::nuevoEstandar, engine);

        get(CONSUMO, ConsumoPorPeriodoController::mostrar, engine);
        get(CONSUMO_OBTENER, ConsumoPorPeriodoController::obtener, engine);

        get(TRANSFORMADOR, TransformadorController::mostrar, engine);

        get(USER, PanelDeUsuarioController::mostrar, engine);
        get(USER_EDIT, PanelDeUsuarioController::editar, engine);
        post(USER_EDIT, PanelDeUsuarioController::actualizar);

        get(SIGNUP, RegistrarController::mostrar, engine);
        post(SIGNUP, RegistrarController::registrar, engine);

        get(LOGOUT, LoginController::logout, engine);

        get(LIFE, Controller::fortyTwo, engine);
        get(GLITCH, ErrorController::somethingBroke, engine);
    }

    public void run() {
        //Para debuggear localhost
        Spark.port(9000);

        //Para el deploy en heroku
        //port(getHerokuAssignedPort());
        Spark.staticFiles.location("/templates");
        DebugScreen.enableDebugScreen();

        new PersistirMain().initialize();

        this.inicialiarControladores();
        this.router();
    }

}
