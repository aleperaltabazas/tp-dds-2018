package DDS.SGE.Utils;

import DDS.SGE.Dispositivo.*;
import DDS.SGE.Dispositivo.Estado.*;
import DDS.SGE.Fabricante.*;
import DDS.SGE.Geoposicionamiento.Transformador;
import DDS.SGE.Repositorios.*;
import DDS.SGE.Usuarie.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistirMain implements WithGlobalEntityManager, TransactionalOps {
    private static final Logger LOGGER = Logger.getLogger(PersistirMain.class.getName());

    public void initialize() {
        TablaDispositivos td = new TablaDispositivos();

        ClienteBuilder cb = new ClienteBuilder();

        Cliente c1 = cb.crearCliente("Alejandro", "Peralta Bazas", "4012972", "16729076", "Alesaurio", "pass");
        Cliente c2 = cb.crearCliente("Matias", "Giorda", "12927397", "47820726", "maticrash", "otrapass");

        Dispositivo d = new Dispositivo("Dispositivo cualunca", new DispositivoEstandar(20, 40));
        Dispositivo d2 = new Dispositivo("Otro dispositivo", new DispositivoEstandar(50, 100));

        c1.agregarDispositivo(d);
        c1.agregarDispositivo(d2);

        c1.setTieneNotificaciones(true);

        DispositivoInteligente dispositivoInfractorInteligente = new DispositivoInteligente(new Encendido(), new AireAcondicionado(100, 2));
        Dispositivo dispositivoInfractor = new Dispositivo(dispositivoInfractorInteligente);

        Cliente clienteConDispositivoInfractor = cb.crearCliente("Maximiliano", "Paz", "420", "386", "foo", "bar");
        clienteConDispositivoInfractor.agregarDispositivo(dispositivoInfractor);
        clienteConDispositivoInfractor.agregarDispositivo(td.getDispositivos().get(2).construir());

        Fabricante unFabricante = new Computadora(true);
        LocalDateTime fechaDeReferencia = LocalDateTime.now();
        IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
        IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5), fechaDeReferencia.minusHours(3));
        List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
        RepositorioDeTiempoEncendido repositorioDePrueba = new RepositorioDeTiempoEncendido();
        repositorioDePrueba.setIntervalosDeActividad(intervalosDeActividad);
        DispositivoInteligente tipo = new DispositivoInteligente(new Encendido(), unFabricante);

        Dispositivo di = new Dispositivo("Inteligente", new DispositivoInteligente(new Apagado(), new AireAcondicionado(3500, 60)));
        tipo.setRepositorio(repositorioDePrueba);
        di.setTipoDispositvo(tipo);

        c2.agregarDispositivo(di);

        AdministradorBuilder ab = new AdministradorBuilder();
        Administrador admin = ab.admin("Gastón", "Prieto", "admin", "admin");

        Transformador transformador1 = new Transformador(1);
        Transformador transformador2 = new Transformador(2);
        Transformador transformador3 = new Transformador(3);
        Transformador transformador4 = new Transformador(4);

        Transformador transformador5 = new Transformador(5);

        transformador1.agregarCliente(c1);
        transformador2.agregarCliente(c2);

        List<Transformador> transformadores = Arrays.asList(transformador1, transformador2, transformador3, transformador4, transformador5);
        List<Cliente> clientes = Arrays.asList(c1, c2, clienteConDispositivoInfractor);
        List<Administrador> administradores = Arrays.asList(admin);

        try {
            withTransaction(() -> {
                td.getDispositivos().forEach(dispo -> RepositorioCatalogo.getInstance().saveOrUpdate(dispo));
                clientes.forEach(c -> RepositorioClientes.getInstance().registrarCliente(c));
                administradores.forEach(a -> RepositorioAdministradores.getInstance().registrarAdministrador(a));
                transformadores.forEach(t -> RepositorioTransformadores.getInstance().saveOrUpdate(t));
            });
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }
}
