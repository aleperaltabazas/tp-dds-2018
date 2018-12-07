package DDS.SGE.Persistencia;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.EntityManagerHelper;
import DDS.SGE.Dispositivos.RepositorioDeTiempoEncendidoTest;
import DDS.SGE.Usuarie.Cliente.TipoDni;
import DDS.SGE.Dispositivo.IntervaloActivo;
import DDS.SGE.Geoposicionamiento.Transformador;

public class TestTransformador {

    Cliente clienteSinDispositivos;

    LocalDateTime fechaDeReferencia = LocalDateTime.now();
    IntervaloActivo intervaloDe1Hora = new IntervaloActivo(fechaDeReferencia.minusHours(1), fechaDeReferencia);
    IntervaloActivo intervaloDe2Horas = new IntervaloActivo(fechaDeReferencia.minusHours(5),
            fechaDeReferencia.minusHours(3));
    List<IntervaloActivo> intervalosDeActividad = Arrays.asList(intervaloDe1Hora, intervaloDe2Horas);
    RepositorioDeTiempoEncendidoTest repositorioDePrueba = new RepositorioDeTiempoEncendidoTest(intervalosDeActividad);

    Transformador transformador_1 = new Transformador(1);
    Transformador transformador_2 = new Transformador(2);
    Transformador transformador_3 = new Transformador(3);
    Transformador transformador_4 = new Transformador(4);

    @Before
    public void Inicializar() {
        clienteSinDispositivos = new Cliente("Alejandro", "Peralta", TipoDni.DNI, "123456789", "1144448888",
                "Av siempre viva 742", LocalDateTime.now(), Arrays.asList());

    }

    @Test
    public void PersistirCorrectamenteLosTransformadores() {
        /*

        TODO: pasarlo a la versión bien de persistencia
        EntityManager em = EntityManagerHelper.entityManager();

        transformador_1.agregarCliente(clienteSinDispositivos);

        em.persist(clienteSinDispositivos);

        em.persist(transformador_1);
        em.persist(transformador_2);
        em.persist(transformador_3);

        TypedQuery<Long> query1 = em.createQuery("SELECT COUNT(t.id) FROM Transformador t", Long.class);
        Long cantidadDeTransformadoresPersistidosVersion1 = query1.getSingleResult();

        em.persist(transformador_4);
        TypedQuery<Long> query2 = em.createQuery("SELECT COUNT(t.id) FROM Transformador t", Long.class);
        Long cantidadDeTransformadoresPersistidosVersion2 = query2.getSingleResult();

        int a = cantidadDeTransformadoresPersistidosVersion1.intValue();
        int b = cantidadDeTransformadoresPersistidosVersion2.intValue();

        assertEquals(a + 1, b);
        */
    }
}
