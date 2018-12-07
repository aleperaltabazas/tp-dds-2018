package DDS.SGE.Geoposicionamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import DDS.SGE.EntityManagerHelper;

public class RepositorioTransformadores {
	List<Transformador> transformadores = new ArrayList<Transformador>(Arrays.asList());
	EntityManager em = EntityManagerHelper.entityManager();
	
	
	private RepositorioTransformadores() {
		TypedQuery<Transformador> query = em.createQuery("SELECT t FROM Transformador t", Transformador.class);
		transformadores = query.getResultList();
	}

	public static RepositorioTransformadores instancia = new RepositorioTransformadores();

	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public void setTransformadores(List<Transformador> transformador) {
		this.transformadores = transformador;
	}

	public void agregarTransformador(Transformador transformador) {
		transformadores.add(transformador);
	}
	
	public Transformador getTransformador (long idTransformador) {
		return transformadores.stream().filter(transformador -> transformador.getId() == idTransformador).findFirst().orElse(null);
	}
	
}
