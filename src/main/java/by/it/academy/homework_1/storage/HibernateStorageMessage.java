package by.it.academy.homework_1.storage;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.api.IStorageMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateStorageMessage implements IStorageMessage {
    private static final HibernateStorageMessage instance = new HibernateStorageMessage();

    private HibernateStorageMessage() {
    }

    @Override
    public List<Message> get(String loginUser) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Message> data = entityManager.
                createQuery("SELECT obj FROM Message obj WHERE obj.to LIKE ?1", Message.class).
                setParameter(1, loginUser).
                getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return data;
    }

    @Override
    public void add(Message message) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(message);

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

        int result = entityManager.createQuery("select count(obj) from Message obj").getFirstResult();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return result;
    }

    public static HibernateStorageMessage getInstance() {
        return instance;
    }
}
