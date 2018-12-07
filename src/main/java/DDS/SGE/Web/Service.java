package DDS.SGE.Web;

import static DDS.SGE.Web.Controllers.Routes.*;
import static spark.Spark.get;
import static spark.Spark.post;

import DDS.SGE.Utils.PersistirMain;
import DDS.SGE.Web.Controllers.*;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
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
        miHogarController = new MiHogarController();
        optimizadorController = new OptimizadorController();
        panelDeAdministradorController = new PanelDeAdministradorController();
        panelDeUsuarioController = new PanelDeUsuarioController();
        registrarController = new RegistrarController();
        transformadorController = new TransformadorController();
    }

    private void inicializarRutas() {
        HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
        HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

        get(HOME, homeController::mostrar, engine);

        get(LOGIN, loginClienteController::mostrar, engine);
        post(LOGIN, loginClienteController::loginCliente, engine);

        get(HOGAR, miHogarController::mostrar, engine);

        get(OPTIMIZADOR, optimizadorController::mostrar, engine);

        get(ADMINISTRADOR_LOGIN, loginAdminController::mostrar, engine);
        post(ADMINISTRADOR_LOGIN, loginAdminController::loginAdmin, engine);

        get(ADMINISTRADOR, panelDeAdministradorController::mostrar, engine);
        get(ADMINISTRADOR_HOGARES, panelDeAdministradorController::verTodosLosHogares, engine);

        get(DISPOSITIVOS, catalogoController::mostrar, engine);
        get(DISPOSITIVOS_ACQUIRE, catalogoController::mostrarAdquirir);
        get(DISPOSITIVOS_NEW_INTELIGENTE, catalogoController::mostrarFormularioInteligente, engine);
        get(DISPOSITIVOS_NEW_ESTANDAR, catalogoController::mostrarFormularioEstandar, engine);
        post(DISPOSITIVOS_NEW_INTELIGENTE, catalogoController::nuevoInteligente, engine);
        post(DISPOSITIVOS_NEW_ESTANDAR, catalogoController::nuevoEstandar, engine);

        get(CONSUMO, consumoPorPeriodoController::mostrar, engine);
        get(CONSUMO_OBTENER, consumoPorPeriodoController::obtener, engine);

        get(TRANSFORMADOR, transformadorController::mostrar, engine);

        get(USER, panelDeUsuarioController::mostrar, engine);
        get(USER_EDIT, panelDeUsuarioController::editar, engine);
        post(USER_EDIT, panelDeUsuarioController::actualizar);

        get(SIGNUP, registrarController::mostrar, engine);
        post(SIGNUP, registrarController::registrar, engine);

        get(LOGOUT, loginController::logout, engine);

        //TODO: get(LIFE, controller::fortyTwo, engine);
        get(GLITCH, errorController::somethingBroke, engine);
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
