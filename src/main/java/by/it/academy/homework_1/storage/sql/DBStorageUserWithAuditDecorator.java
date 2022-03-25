package by.it.academy.homework_1.storage.sql;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.sql.api.SQLDBInitializer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBStorageUserWithAuditDecorator implements IStorageUser {
    private static final DBStorageUserWithAuditDecorator instance = new DBStorageUserWithAuditDecorator();

    private final DBStorageUser storageUser;
    private final DBAuditUserStorage auditUserStorage;
    private final DataSource dataSource;

    private DBStorageUserWithAuditDecorator() {
        this.storageUser = DBStorageUser.getInstance();
        this.auditUserStorage = DBAuditUserStorage.getInstance();
        this.dataSource = SQLDBInitializer.getInstance().getDataSource();
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

    public static DBStorageUserWithAuditDecorator getInstance() {
        return instance;
    }
}
