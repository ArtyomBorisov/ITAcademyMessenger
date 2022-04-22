package by.it.academy.homework_1.dao.sql;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageUser;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository("dbStorageUserWithAudit")
public class DBStorageUserWithAuditDecorator implements IStorageUser {

    private final IStorageUser storageUser;
    private final IAuditUserStorage auditUserStorage;

    public DBStorageUserWithAuditDecorator(DBStorageUser storageUser,
                                           DBAuditUserStorage auditUserStorage) {
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
        this.storageUser.create(user);

        AuditUser auditUser = new AuditUser();
        auditUser.setDtCreate(user.getRegistration());
        auditUser.setText("Регистрация");
        auditUser.setUser(user);
        auditUser.setLastUpdate(user.getRegistration());

        this.auditUserStorage.create(auditUser);

        return user;
    }

    @Override
    public long getCount() {
        return this.storageUser.getCount();
    }

    @Override
    public User update(User user, String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.storageUser.update(user, login, lastUpdate);

        AuditUser auditUser = new AuditUser();
        auditUser.setDtCreate(user.getLastUpdate());
        auditUser.setText("Обновление");
        auditUser.setUser(user);
        auditUser.setLastUpdate(user.getLastUpdate());

        this.auditUserStorage.create(auditUser);

        return user;
    }

    @Override
    public void delete(String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.storageUser.delete(login, lastUpdate);

        LocalDateTime now = LocalDateTime.now();
        AuditUser auditUser = new AuditUser();
        auditUser.setDtCreate(now);
        auditUser.setText("Удаление");
        auditUser.setUser(this.storageUser.get("deleted"));
        auditUser.setLastUpdate(now);

        this.auditUserStorage.create(auditUser);
    }
}
