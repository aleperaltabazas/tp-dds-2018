package DDS.SGE.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import DDS.SGE.EntityManagerHelper;

public class Repositorio {
	protected static EntityManager em = EntityManagerHelper.entityManager();

	protected static <T> T findByID(Class<T> entityClass, Long id) {
		return em.find(entityClass, id);
	}

	protected static <T> T findByUsername(Class<T> entityClass, String username) {
		String table = entityClass.getSimpleName();
		String letter = "" + table.charAt(0);

		List<T> objects = em
				.createQuery("from " + table + " " + letter + " where " + letter + ".username LIKE :username",
						entityClass)
				.setParameter("username", username).getResultList();

		return objects.get(0);
	}

	protected static void persistir(Object o) {
		EntityManagerHelper.beginTransaction();
		em.persist(o);
		EntityManagerHelper.commit();
	}
}
