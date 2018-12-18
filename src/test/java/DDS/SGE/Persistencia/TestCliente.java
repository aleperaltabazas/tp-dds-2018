package DDS.SGE.Persistencia;

import DDS.SGE.Dispositivo.Dispositivo;
import DDS.SGE.Dispositivo.DispositivoEstandar;
import DDS.SGE.Dispositivo.DispositivoInteligente;
import DDS.SGE.Dispositivo.Estado.Apagado;
import DDS.SGE.Repositorios.RepositorioAdministradores;
import DDS.SGE.Repositorios.RepositorioClientes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import DDS.SGE.Usuarie.Administrador;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Fabricante.AireAcondicionado;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class TestCliente implements WithGlobalEntityManager, TransactionalOps {
    private Cliente cliente;
    private Dispositivo dispositivoSencillo;
    private Dispositivo noSencillo;
    private DispositivoInteligente inteligente;
    private DispositivoEstandar estandar;

    @Before
    public void initialize() {
        estandar = new DispositivoEstandar(10, 20);
        dispositivoSencillo = new Dispositivo(estandar);
        inteligente = new DispositivoInteligente(new Apagado(), new AireAcondicionado(20));
        noSencillo = new Dispositivo(inteligente);

        cliente = new Cliente("Un nombre", "Un apellido", Cliente.TipoDni.DNI, "1111", "4444", "Un domicilio",
                LocalDateTime.now(), new ArrayList<Dispositivo>());
    }

    @Test
    public void testPersistirAUnClienteDesdeElRepositorioYDespuesTraerlo() {
        withTransaction(() -> RepositorioClientes.getInstance().registrarCliente(cliente));
        Cliente persistido = RepositorioClientes.getInstance().findByID(cliente.getId());

        assertEquals(persistido, cliente);
    }

    @Test
    public void testPersistirUnClienteYTraerloPorUsername() {
        Cliente otroCliente = new Cliente("Usuario", "pass");
        withTransaction(() -> RepositorioClientes.getInstance().agregarCliente(otroCliente));

        Cliente persistido = RepositorioClientes.getInstance().findByUsername(otroCliente.getUsername()).get();
        assertEquals(persistido, otroCliente);

    }

    @Test
    public void testPersistirUnAdministrador() {
        Administrador administrador = new Administrador("Admin", "admin");
        withTransaction(() -> RepositorioAdministradores.getInstance().agregarAdministrador(administrador));
        Administrador persistido = RepositorioAdministradores.getInstance().findByID(administrador.getId());

        assertEquals(administrador, persistido);
    }

    @Test
    public void testPersistirUnClienteYLevantarSusDispositivos() {
        Cliente otroCliente = new Cliente("Mati", "Cash", Cliente.TipoDni.DNI, "121", "1212", "121", LocalDateTime.now(), Arrays.asList(dispositivoSencillo, noSencillo));
        withTransaction(() -> RepositorioClientes.getInstance().agregarCliente(otroCliente));

        List<Dispositivo> dispositivos = RepositorioClientes.getInstance().findByID(otroCliente.getId()).getDispositivos().collect(Collectors.toList());
        assertEquals(Arrays.asList(dispositivoSencillo, noSencillo), dispositivos);
    }

    @Test
    public void testPersistoUnClienteConDispositivosYAlTraerloMeTraeSusDispositivos() {
        Cliente otroCliente = new Cliente("Mati", "Cash", Cliente.TipoDni.DNI, "121", "1212", "121", LocalDateTime.now(), Arrays.asList(dispositivoSencillo, noSencillo));
        withTransaction(() -> RepositorioClientes.getInstance().agregarCliente(otroCliente));

        Cliente persistido = RepositorioClientes.getInstance().findByID(otroCliente.getId());
        assertEquals(persistido.getDispositivos().collect(Collectors.toList()), otroCliente.getDispositivos().collect(Collectors.toList()));
    }

    @Test
    public void testPersistoUnClienteYMeTraigoSusDispositivosPorSeparado() {
        Cliente otroCliente = new Cliente("Mati", "Cash", Cliente.TipoDni.DNI, "121", "1212", "121", LocalDateTime.now(), Arrays.asList(dispositivoSencillo, noSencillo));
        withTransaction(() -> RepositorioClientes.getInstance().agregarCliente(otroCliente));

        Cliente persistido = RepositorioClientes.getInstance().findByID(otroCliente.getId());

        assertEquals(Arrays.asList(dispositivoSencillo), persistido.getDispositivosEstandar());
        assertEquals(Arrays.asList(noSencillo), persistido.getDispositivosInteligente());

    }

}
