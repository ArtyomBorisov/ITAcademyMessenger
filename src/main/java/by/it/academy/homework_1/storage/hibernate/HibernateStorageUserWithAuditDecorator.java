package by.it.academy.homework_1.storage.hibernate;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.storage.hibernate.api.IHibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.api.IHibernateStorageUser;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class HibernateStorageUserWithAuditDecorator implements IStorageUser {
    private static final HibernateStorageUserWithAuditDecorator instance = new HibernateStorageUserWithAuditDecorator();

    private final IHibernateStorageUser storageUser;
    private final IHibernateAuditUserStorage auditUserStorage;

    private HibernateStorageUserWithAuditDecorator() {
        this.storageUser = HibernateStorageUser.getInstance();
        this.auditUserStorage = HibernateAuditUserStorage.getInstance();
    }

    @Override
    public User get(String login) {
        return storageUser.get(login);
    }

    @Override
    public void add(User user) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        try {
            this.storageUser.add(user, manager);
            AuditUser auditUser = new AuditUser(user.getRegistration(), "Регистрация", user, null);
            this.auditUserStorage.create(auditUser, manager);
            manager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new IllegalArgumentException("Ошибка регистрации");
        } finally {
            manager.close();
        }
    }

    @Override
    public long getCount() {
        return storageUser.getCount();
    }

    public static HibernateStorageUserWithAuditDecorator getInstance() {
        return instance;
    }
}
