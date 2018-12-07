package DDS.SGE.Persistencia;

import DDS.SGE.Dispositivo.*;
import DDS.SGE.Dispositivo.Estado.*;
import DDS.SGE.Fabricante.*;
import DDS.SGE.Geoposicionamiento.*;
import DDS.SGE.Dispositivos.RepositorioDeTiempoEncendidoTest;
import DDS.SGE.Repositorios.*;
import DDS.SGE.Usuarie.*;
import DDS.SGE.Usuarie.Cliente.TipoDni;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestDispositivo implements TransactionalOps, WithGlobalEntityManager {

    Cliente clienteSinDispositivos;
    Cliente clienteConUnDispositivoEstandar;
    Cliente clienteConUnDispositivoInteligente;
    Dispositivo dispositivoSencillo;
    Dispositivo dispositivoInteligente;
    DispositivoEstandar estandar = new DispositivoEstandar(10, 20);
    DispositivoInteligente inteligente;
    Transformador unTransformador = new Transformador(200);

    LocalDateTime fechaDeReferencia = LocalDateTime.now();
    IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
    IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5),
            fechaDeReferencia.minusHours(3));
    List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
    RepositorioDeTiempoEncendidoTest repositorioDePrueba = new RepositorioDeTiempoEncendidoTest(intervalosDeActividad);

    @Before
    public void Inicializar() {
        inteligente = new DispositivoInteligente(new Apagado(), new AireAcondicionado(3800));
        dispositivoSencillo = new Dispositivo(estandar);
        dispositivoSencillo.setNombre("Sencillo");

        dispositivoInteligente = new Dispositivo(inteligente);

        clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
                "Av siempre viva 742", LocalDateTime.now(), Arrays.asList());

        clienteConUnDispositivoEstandar = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
                "Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoSencillo));

        clienteConUnDispositivoInteligente = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
                "Av siempre viva 742", LocalDateTime.now(), Arrays.asList(dispositivoInteligente));

        unTransformador.agregarCliente(clienteConUnDispositivoInteligente);

    }

    @Test
    public void PersistirUnUsuarioYLuegoCambiarleGeolocalizacion() {
        withTransaction(() -> RepositorioClientes.getInstance().agregarCliente(clienteSinDispositivos));

        Cliente clientePersistido = RepositorioClientes.getInstance().findByID(clienteSinDispositivos.getId());
        clientePersistido.setDomicilio("calle x");

        withTransaction(() -> RepositorioClientes.getInstance().actualizarCliente(clientePersistido));

        Cliente clienteActualizado = RepositorioClientes.getInstance().findByID(clienteSinDispositivos.getId());

        assertEquals("calle x", clienteActualizado.getDomicilio());
    }

    @Test
    public void PersistirUnDispositivoEstandarYLuegoLevantarlo() {
        withTransaction(() -> RepositorioDispositivos.getInstance().guardarDispositivo(dispositivoSencillo));

        Dispositivo dispositivoPersistido = RepositorioDispositivos.getInstance().findByID(dispositivoSencillo.getId());
        dispositivoPersistido.setNombre("Sencillamente actualizado");
        withTransaction(() -> RepositorioDispositivos.getInstance().guardarDispositivo(dispositivoPersistido));

        Dispositivo dispositivoActualizado = RepositorioDispositivos.getInstance().findByID(dispositivoSencillo.getId());

        assertEquals("Sencillamente actualizado", dispositivoActualizado.getNombre());
    }

    @Test
    public void PersistirUnDispositivoInteligenteYLuegoLevantarlo() {
        withTransaction(() -> RepositorioDispositivos.getInstance().guardarDispositivo(dispositivoInteligente));

        Dispositivo dispositivoPersistido = RepositorioDispositivos.getInstance().findByID(dispositivoInteligente.getId());
        dispositivoPersistido.setTiempoQueSePuedeUtilizar(20);
        withTransaction(() -> RepositorioDispositivos.getInstance().guardarDispositivo(dispositivoPersistido));

        Dispositivo dispositivoActualizado = RepositorioDispositivos.getInstance().findByID(dispositivoPersistido.getId());
        assertEquals(20, (int) dispositivoActualizado.getTiempoQueSePuedeUtilizar());
    }

    @Test
    public void PersistirUnDispositivoInteligenteYLuegoLevantarloConSuRepositorioDeIntervalosActualizado() {
        withTransaction(() -> RepositorioDispositivos.getInstance().guardarDispositivo(dispositivoInteligente));

        Dispositivo dispositivoPersistido = RepositorioDispositivos.getInstance().findByID(dispositivoInteligente.getId());
        DispositivoInteligente dispositivoInteligentePersistido = (DispositivoInteligente) dispositivoPersistido
                .getTipoDispositivo();

        assertEquals(0, dispositivoPersistido.consumoTotalHaceNHoras(100), 0);

        dispositivoInteligentePersistido.setRepositorio(repositorioDePrueba);
        Dispositivo dispositivoPersistidoActual = RepositorioDispositivos.getInstance().findByID(dispositivoPersistido.getId());

        assertEquals(180 * inteligente.getConsumoKWPorHora(), dispositivoPersistidoActual.consumoTotalHaceNHoras(100),
                0);
    }

    @Test
    public void testPersistirUnDispositivoDeCatalogoYTraerloDeVuelta() {
        DispositivoDeCatalogo dispositivo = new DispositivoDeCatalogo("Un dispositivo", 100, false, true, MetodoDeCreacion.AIRE);

        withTransaction(() -> RepositorioCatalogo.getInstance().agregarDispositivoAlCatalogo(dispositivo));

        DispositivoDeCatalogo persistido = RepositorioCatalogo.getInstance().findByID(dispositivo.getId());
        assertEquals(persistido, dispositivo);

    }
}
