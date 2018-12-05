package DDS.SGE.Web;

import static DDS.SGE.Web.Controllers.Routes.*;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.*;
import DDS.SGE.Cliente.TipoDni;
import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Dispositivo.RepositorioDeTiempoEncendido;
import DDS.SGE.Dispositivo.TablaDispositivos;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import DDS.SGE.Web.Controllers.*;
import Fabricante.Computadora;
import Fabricante.Fabricante;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Service {
    public static void main(String[] args) {
        //Para debuggear localhost
        Spark.port(9000);

        //Para el deploy en heroku
        //port(getHerokuAssignedPort());
        Spark.staticFiles.location("/templates");
        DebugScreen.enableDebugScreen();

        HandlebarsTemplateEngineBuilder builder = new HandlebarsTemplateEngineBuilder(new HandlebarsTemplateEngine());
        HandlebarsTemplateEngine engine = builder.withDefaultHelpers().build();

        initialize();

        get(HOME, HomeController::mostrar, engine);

        get(LOGIN, LoginClienteController::mostrar, engine);
        post(LOGIN, LoginClienteController::loginCliente, engine);

        get(HOGAR, HogarController::mostrar, engine);

        get(OPTIMIZADOR, OptimizadorController::mostrar, engine);

        get(ADMINISTRADOR_LOGIN, LoginAdminController::mostrar, engine);
        post(ADMINISTRADOR_LOGIN, LoginAdminController::loginAdmin, engine);

        get(ADMINISTRADOR, PanelAdministradorController::mostrar, engine);
        get(ADMINISTRADOR_HOGARES, PanelAdministradorController::verTodosLosHogares, engine);

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

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    private static void initialize() {
        Cliente cliente = new Cliente("Matias", "Giorda", TipoDni.DNI, "123454321", "1155667788", "Calle verdadera 321",
                LocalDateTime.now(), Arrays.asList());
        RepositorioClientes.getInstance().agregarCliente(cliente);

        ClienteBuilder cb = new ClienteBuilder();

        Cliente c1 = cb.crearCliente("Alejandro", "Peralta Bazas", "4012972", "16729076", "Alesaurio", "pass");
        Cliente c2 = cb.crearCliente("Matias", "Giorda", "12927397", "47820726", "maticrash", "otrapass");

        Dispositivo d = new Dispositivo(new DispositivoEstandar(20, 40));
        d.setNombre("Dispositivo cualunca");

        Dispositivo d2 = new Dispositivo(new DispositivoEstandar(50, 100));
        d2.setNombre("Otro dispositivo");

        c1.agregarDispositivo(d);
        c1.agregarDispositivo(d2);

        AdministradorBuilder ab = new AdministradorBuilder();
        Administrador admin = ab.admin("Gastón", "Prieto", "admin", "admin");

        TablaDispositivos td = new TablaDispositivos();
        td.getDispositivos().forEach(dispo -> RepositorioDispositivos.getInstance().agregarDispositivoAlCatalogo(dispo));

        Fabricante unFabricante = new Computadora(true);
        LocalDateTime fechaDeReferencia = LocalDateTime.now();
        IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
        IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5), fechaDeReferencia.minusHours(3));
        List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
        RepositorioDeTiempoEncendido repositorioDePrueba = new RepositorioDeTiempoEncendido();
        repositorioDePrueba.setIntervalosDeActividad(intervalosDeActividad);
        DispositivoInteligente tipo = new DispositivoInteligente(new Encendido(), unFabricante);

        Dispositivo di = td.getDispositivos().get(0);
        tipo.setRepositorio(repositorioDePrueba);
        di.setTipoDispositvo(tipo);

        c2.agregarDispositivo(di);
        c2.agregarDispositivo(td.getDispositivos().get(5));


        try {
            RepositorioClientes.getInstance().registrarCliente(c1);
            RepositorioClientes.getInstance().registrarCliente(c2);
            RepositorioAdministradores.getInstance().registrarAdministrador(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
