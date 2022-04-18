package by.it.academy.homework_1.dao.hibernate;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.dao.hibernate.api.IHibernateAuditUserStorage;
import by.it.academy.homework_1.dao.hibernate.api.IHibernateStorageUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository("hibernateStorageUserWithAuditDecorator")
public class HibernateStorageUserWithAuditDecorator implements IStorageUser {

    private final IHibernateStorageUser storageUser;
    private final IHibernateAuditUserStorage auditUserStorage;

    public HibernateStorageUserWithAuditDecorator(IHibernateStorageUser storageUser,
                                                  IHibernateAuditUserStorage auditUserStorage) {
        this.storageUser = storageUser;
        this.auditUserStorage = auditUserStorage;
    }

    @Override
    public User get(String login) {
        return this.storageUser.get(login);
    }

    @Override
    public Collection<User> getAll() {
        return this.storageUser.getAll();
    }

    @Override
    public User create(User user) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            this.storageUser.create(user, manager);

            AuditUser auditUser = new AuditUser();
            auditUser.setDtCreate(user.getRegistration());
            auditUser.setText("Регистрация");
            auditUser.setUser(user);
            auditUser.setLastUpdate(user.getRegistration());

            this.auditUserStorage.create(auditUser, manager);
            manager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new IllegalArgumentException("Ошибка регистрации");
        } finally {
            manager.close();
        }

        return this.get(user.getLogin());
    }

    @Override
    public long getCount() {
        return this.storageUser.getCount();
    }

    @Override
    public User update(User item, String s, LocalDateTime lastUpdate) throws EssenceNotFound {
        return null;
    }

    @Override
    public void delete(String s, LocalDateTime lastUpdate) throws EssenceNotFound {

    }
}
