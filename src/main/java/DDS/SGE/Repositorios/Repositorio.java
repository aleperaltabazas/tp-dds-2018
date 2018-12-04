package DDS.SGE.Repositorios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;

import DDS.SGE.EntityManagerHelper;

public class Repositorio {
    protected static EntityManager em = EntityManagerHelper.entityManager();

    protected static <T> T findByID(Class<T> entityClass, Long id) {
        return em.find(entityClass, id);
    }

    protected static <T> Optional<T> findByUsername(Class<T> entityClass, String username) {
        String table = entityClass.getSimpleName();
        String letter = "" + table.charAt(0);

        List<T> objects = em
                .createQuery("from " + table + " " + letter + " where " + letter + ".username LIKE :username",
                        entityClass)
                .setParameter("username", username).getResultList();

        try {
            return Optional.ofNullable(objects.get(0));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }

    }

    protected static void persistir(Object o) {
        em.getTransaction().begin();
        em.persist(o);
        //em.flush();
        em.getTransaction().commit();
    }

    protected static void registrar(Object o, String username) throws Exception {
        try {
            findByUsername(o.getClass(), username).get();
            throw new Exception("Ese nombre de usuario no se encuentra disponible");
        } catch (NoSuchElementException e) {
            persistir(o);
        }
    }
}
