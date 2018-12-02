package DDS.SGE.Repositorios;

import DDS.SGE.Cliente;
import DDS.SGE.Dispositivo.Dispositivo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.Arrays;
import java.util.List;

public class RepositorioDispositivos extends Repositorio implements WithGlobalEntityManager {
    public static List<Dispositivo> listar() {
        return em.createQuery("from Dispositivo", Dispositivo.class).getResultList();
    }

    public static List<Dispositivo> dispositivosDe(Cliente cliente) {
        return em.createQuery("from Dispositivo d where d.id = " + cliente.getId()).getResultList();
    }
}
