package DDS.SGE.Repositorios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS.SGE.EntityManagerHelper;

public class Repositorio implements WithGlobalEntityManager {
    protected <T> T findByID(Class<T> entityClass, Long id) {
        return entityManager().find(entityClass, id);
    }

    protected <T> Optional<T> findByUsername(Class<T> entityClass, String username) {
        String table = entityClass.getSimpleName();
        String letter = "" + table.charAt(0);

        List<T> objects = entityManager()
                .createQuery("from " + table + " " + letter + " where " + letter + ".username LIKE :username",
                        entityClass)
                .setParameter("username", username).getResultList();

        try {
            return Optional.ofNullable(objects.get(0));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }

    }

    protected void persistir(Object o) {
    	entityManager().persist(o);
    }

    protected void registrar(Object o, String username) throws Exception {
        try {
            findByUsername(o.getClass(), username).get();
            throw new Exception("Ese nombre de usuario no se encuentra disponible");
        } catch (NoSuchElementException e) {
            persistir(o);
        }
    }
}
