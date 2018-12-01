package DDS.SGE.Repositorios;

import javax.persistence.EntityManager;

import DDS.SGE.EntityManagerHelper;

public class Repositorio {
	protected static EntityManager em = EntityManagerHelper.entityManager();
	
	public static <T> T findByID(Class<T> entityClass, Long id) {
		return em.find(entityClass, id);
	}
}
