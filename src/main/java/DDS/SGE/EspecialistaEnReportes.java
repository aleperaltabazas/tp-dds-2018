package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DDS.SGE.Repositorios.RepositorioClientes;
import DDS.SGE.Usuarie.Cliente;
import DDS.SGE.Repositorios.RepositorioTransformadores;
import DDS.SGE.Geoposicionamiento.Transformador;

public class EspecialistaEnReportes {
    private List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList());
    private List<Transformador> transformadores = new ArrayList<Transformador>(Arrays.asList());

    public EspecialistaEnReportes() {
        clientes = RepositorioClientes.getInstance().getAllClients();
        transformadores = RepositorioTransformadores.getInstance().listar();
    }

    public double obtenerElConsumoTotalDeTodosLosClientesEnUnPeriodo(int periodoEnDias) {
        return clientes.stream().mapToDouble(cliente -> cliente.consumoFinalEstimado(periodoEnDias)).sum();
    }

    public double obtenerElConsumoPromedioPorDispositivoDeUnCliente(long idCliente) {
        Cliente unCliente = RepositorioClientes.getInstance().findByID(idCliente);
        if (unCliente == null)
            return 0;
        return unCliente.consumoPromedioPorDispositivo();
    }

    public double obtenerElConsumoPorTransformadorPorPeriodo(long idTransformador) {
        Transformador unTransformador = RepositorioTransformadores.getInstance().findByID(idTransformador);

        if (unTransformador == null)
            return 0;
        return unTransformador.suministra();
    }

}
