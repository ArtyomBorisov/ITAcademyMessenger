package by.it.academy.homework_1.storage;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateStorageUser implements IStorageUser {
    private static final HibernateStorageUser instance = new HibernateStorageUser();

    private HibernateStorageUser() {
    }

    @Override
    public User get(String login) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User result = entityManager.
                createQuery("SELECT obj FROM User obj WHERE obj.login LIKE ?1", User.class).
                setParameter(1, login).
                getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return result;
    }

    @Override
    public void add(User user) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }

    @Override
    public long getCount() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        int result = entityManager.createQuery("select count(obj) from User obj").getFirstResult();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return result;
    }

    public static HibernateStorageUser getInstance() {
        return instance;
    }
}
