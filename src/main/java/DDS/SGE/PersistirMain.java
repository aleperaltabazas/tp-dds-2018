package DDS.SGE;

import DDS.SGE.Dispositivo.*;
import DDS.SGE.Dispositivo.Estado.Encendido;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Repositorios.RepositorioDispositivos;
import Fabricante.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

public class PersistirMain implements WithGlobalEntityManager, TransactionalOps {
    private static final Logger logger = Logger.getLogger(PersistirMain.class.getName());

    public void initialize() {
        ClienteBuilder cb = new ClienteBuilder();

        Cliente c1 = cb.crearCliente("Alejandro", "Peralta Bazas", "4012972", "16729076", "Alesaurio", "pass");
        Cliente c2 = cb.crearCliente("Matias", "Giorda", "12927397", "47820726", "maticrash", "otrapass");

        Dispositivo d = new Dispositivo("Dispositivo cualunca", new DispositivoEstandar(20, 40));

        Dispositivo d2 = new Dispositivo("Otro dispositivo", new DispositivoEstandar(50, 100));

        c1.agregarDispositivo(d);
        c1.agregarDispositivo(d2);

        AdministradorBuilder ab = new AdministradorBuilder();
        Administrador admin = ab.admin("Gastón", "Prieto", "admin", "admin");

        TablaDispositivos td = new TablaDispositivos();

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
            withTransaction(() -> {
                td.getDispositivos().forEach(dispo -> RepositorioDispositivos.getInstance().agregarDispositivoAlCatalogo(dispo));
                RepositorioClientes.getInstance().registrarCliente(c1);
                RepositorioClientes.getInstance().registrarCliente(c2);
                RepositorioAdministradores.getInstance().registrarAdministrador(admin);
            });
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

}
