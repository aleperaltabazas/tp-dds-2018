package DDS.SGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Geoposicionamiento.RepositorioTransformadores;
import Geoposicionamiento.Transformador;

public class EspecialistaEnReportes {
	private List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList());
	private List<Transformador> transformadores = new ArrayList<Transformador>(Arrays.asList());
	
	public EspecialistaEnReportes(){
		clientes = RepositorioClientes.instancia.getClientes();
		transformadores = RepositorioTransformadores.instancia.getTransformadores();
	}
	
	public double obtenerElConsumoTotalDeTodosLosClientesEnUnPeriodo(int periodoEnDias) {
		return clientes.stream().mapToDouble(x -> x.consumoFinalEstimado(periodoEnDias)).sum();
	}
	
	public double obtenerElConsumoPromedioPorDispositivoDeUnCliente(long idCliente) {
		Cliente unCliente = RepositorioClientes.instancia.getCliente(idCliente);
		if (unCliente == null) 
			return 0;
		return unCliente.consumoPromedioPorDispositivo();
	}
	
	public double obtenerElConsumoPorTransformadorPorPeriodo(long idTransformador) {
		Transformador unTransformador = RepositorioTransformadores.instancia.getTransformador(idTransformador);
		if(unTransformador == null)
			return 0;
		return unTransformador.suministra();
	}
	
}