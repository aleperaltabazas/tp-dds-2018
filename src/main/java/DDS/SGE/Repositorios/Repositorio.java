package DDS.SGE.Repositorios;

import DDS.SGE.Usuarie.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

abstract class Repositorio implements WithGlobalEntityManager {
    protected <T> T findByID(Class<T> entityClass, Long id) {
        return entityManager().find(entityClass, id);
    }

    protected <T> List<T> findAll(Class<T> entityClass) {
        return entityManager().createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
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

    public void delete(Object o) {
        entityManager().remove(o);
    }

    protected void persistir(Object o) {
        entityManager().persist(o);
    }

    protected void registrar(Object o, String username) {
        if (findByUsername(o.getClass(), username).isPresent()) {
            System.out.println(((Cliente) findByUsername(o.getClass(), username).get()).getUsername());
            throw new RuntimeException("Ese nombre de usuario no se encuentra disponible");
        } else {
            persistir(o);
        }
    }
}
