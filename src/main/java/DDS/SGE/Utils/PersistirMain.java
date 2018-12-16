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

        Dispositivo di = new Dispositivo("Inteligente", new DispositivoInteligente(new Apagado(), new AireAcondicionado(3500, 60)));
        tipo.setRepositorio(repositorioDePrueba);
        di.setTipoDispositvo(tipo);

        c2.agregarDispositivo(di);

        Transformador transformador1 = new Transformador(1);
        Transformador transformador2 = new Transformador(2);

        transformador1.agregarCliente(c1);
        transformador2.agregarCliente(c2);

        try {
            withTransaction(() -> {
                td.getDispositivos().forEach(dispo -> RepositorioCatalogo.getInstance().agregarDispositivoAlCatalogo(dispo));

                RepositorioAdministradores.getInstance().registrarAdministrador(admin);

                RepositorioTransformadores.getInstance().agregarTransformador(transformador1);
                RepositorioTransformadores.getInstance().agregarTransformador(transformador2);
            });
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        System.out.println(transformador1.getId());
        System.out.println(transformador2.getId());
    }

}
