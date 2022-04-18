package by.it.academy.homework_1.dao.sql;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

//    @Transactional(rollbackFor = Exception.class)
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
    public User update(User user, String id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.storageUser.update(user, id, lastUpdate);

        LocalDateTime now = LocalDateTime.now();
        AuditUser auditUser = new AuditUser();
        auditUser.setDtCreate(now);
        auditUser.setText("Обновление пользователя");
        auditUser.setUser(user);
        auditUser.setLastUpdate(now);

        this.auditUserStorage.create(auditUser);

        return user;
    }

    @Override
    public void delete(String id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.storageUser.delete(id, lastUpdate);

        LocalDateTime now = LocalDateTime.now();
        AuditUser auditUser = new AuditUser();
        auditUser.setDtCreate(now);
        auditUser.setText("Удаление пользователя");
        auditUser.setUser(this.storageUser.get(id));
        auditUser.setLastUpdate(now);

        this.auditUserStorage.create(auditUser);
    }
}
