package by.it.academy.homework_1.storage.sql;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.sql.api.IDBAuditUserStorage;
import by.it.academy.homework_1.storage.sql.api.IDBStorageUser;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBStorageUserWithAuditDecorator implements IStorageUser {

    private final IDBStorageUser storageUser;
    private final IDBAuditUserStorage auditUserStorage;
    private final DataSource dataSource;

    public DBStorageUserWithAuditDecorator(IDBStorageUser storageUser,
                                            IDBAuditUserStorage auditUserStorage,
                                            DataSource dataSource) {
        this.storageUser = storageUser;
        this.auditUserStorage = auditUserStorage;
        this.dataSource = dataSource;
    }

    @Override
    public User get(String login) {
        return this.storageUser.get(login);
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection())
        {
            conn.setAutoCommit(false);

            this.storageUser.add(user, conn);

            try {
                this.auditUserStorage.create(new AuditUser(user.getRegistration(),
                                "Регистрация",
                                user,
                                null),
                        conn);
            } catch (RuntimeException e) {
                conn.rollback();
                throw new IllegalArgumentException("Не удалось создать аудит");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка подключения к БД");
        }
    }

    @Override
    public long getCount() {
        return this.storageUser.getCount();
    }
}
