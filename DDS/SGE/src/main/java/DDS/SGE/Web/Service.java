package DDS.SGE.Web;

import static DDS.SGE.Web.Controllers.Routes.*;
import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.Arrays;

import DDS.SGE.*;
import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Web.Controllers.*;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
    public static void main(String[] args) {
        Spark.port(9000);
        Spark.staticFiles.location("/templates");
        DebugScreen.enableDebugScreen();

        HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
        HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

        Cliente cliente = new Cliente("Matias", "Giorda", TipoDni.DNI, "123454321", "1155667788", "Calle verdadera 321",
                LocalDateTime.now(), Arrays.asList());
        RepositorioClientes.agregarCliente(cliente);

        ClienteBuilder cb = new ClienteBuilder();

        Cliente c1 = cb.crearCliente("Alejandro", "Peralta Bazas", "4012972", "16729076", "Alesaurio", "pass");
        Cliente c2 = cb.crearCliente("Matias", "Giorda", "12927397", "47820726", "maticrash", "otrapass");

        Dispositivo d = new Dispositivo(new DispositivoEstandar(20, 40));
        d.setNombre("Dispositivo cualunca");

        Dispositivo d2 = new Dispositivo(new DispositivoEstandar(50, 100));
        d2.setNombre("Otro dispositivo");

        c1.agregarDispositivo(d);
        c1.agregarDispositivo(d2);

        try {
            RepositorioClientes.registrarCliente(c1);
            RepositorioClientes.registrarCliente(c2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        get(HOME, HomeController::mostrar, engine);

        get(LOGIN, LoginClienteController::mostrar, engine);
        post(LOGIN, LoginClienteController::loginCliente, engine);

        get(HOGAR, HogarController::mostrar, engine);

        get(OPTIMIZADOR, OptimizadorController::mostrar, engine);

        get(ADMINISTRADOR_LOGIN, LoginAdminController::loginAdmin, engine);

        get(ADMINISTRADOR, PanelAdministradorController::mostrar, engine);
        get(ADMINISTRADOR_HOGARES, PanelAdministradorController::verTodosLosHogares, engine);

        get(DISPOSITIVOS_NEW, CatalogoController::mostrar, engine);

        get(CONSUMO, ConsumoPorPeriodoController::mostrar, engine);
        // get("/consumo/:id",ConsumoPorPeriodoController::obtener, engine);

        get(TRANSFORMADOR, TransformadorController::mostrar, engine);

        get(USER, PanelDeUsuarioController::mostrar, engine);
        get(USER_EDIT, PanelDeUsuarioController::editar, engine);
        post(USER_EDIT, PanelDeUsuarioController::actualizar);

        get(SIGNUP, RegistrarController::mostrar, engine);
        post(SIGNUP, RegistrarController::registrar, engine);

    }
}
