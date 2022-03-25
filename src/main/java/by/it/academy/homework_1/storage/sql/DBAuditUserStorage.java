package by.it.academy.homework_1.storage.sql;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.sql.api.SQLDBInitializer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DBAuditUserStorage implements IAuditUserStorage {

    private static final DBAuditUserStorage instance = new DBAuditUserStorage();
    private final DataSource dataSource;

    public DBAuditUserStorage() {
        this.dataSource = SQLDBInitializer.getInstance().getDataSource();
    }

    @Override
    public Long create(AuditUser auditUser) {
        try (Connection conn = dataSource.getConnection()) {
            return create(auditUser, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД");
        }
    }

    //для DBStorageUserWithAuditDecorator
    public Long create(AuditUser auditUser, Connection conn) {
        if(conn == null){
            return create(auditUser);
        }
        if(auditUser == null){
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO app.audit_users(text, author, dt_create, \"user\")\n" +
                "\tVALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
        ) {
            ps.setObject(1, auditUser.getText());
            ps.setObject(2, auditUser.getAuthor() != null ? auditUser.getAuthor().getLogin() : null);
            ps.setObject(3, auditUser.getDtCreate());
            ps.setObject(4, auditUser.getUser().getLogin());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL");
        }
    }

    @Override
    public List<AuditUser> read(Pageable pageable) {
        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }

            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        List<AuditUser> data = new ArrayList<>();
        String sql = "SELECT\n" +
                "    audit.id AS audit_id,\n" +
                "    audit.text,\n" +
                "    audit.author,\n" +
                "    audit.dt_create,\n" +
                "    audit.user,\n" +
                "    obj.date_reg AS obj_dt_reg,\n" +
                "    obj.fio AS obj_fio,\n" +
                "    obj.birthday AS obj_birthday,\n" +
                "    author.date_reg AS author_dt_reg,\n" +
                "    author.fio AS author_fio,\n" +
                "    author.birthday AS author_birthday\n" +
                "FROM\n" +
                "    app.audit_users AS audit\n" +
                "    LEFT JOIN app.users AS author ON audit.author = author.login\n" +
                "    LEFT JOIN app.users AS obj ON audit.author = obj.login";

        if (limit != null) {
            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            int index = 1;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("audit_id");
                    LocalDateTime dtCreate = rs.getObject("dt_create", LocalDateTime.class);
                    String text = rs.getString("text");
                    User user = User.Builder.createBuilder()
                            .setLogin(rs.getString("user"))
                            .setBirthday(rs.getObject("obj_birthday", LocalDate.class))
                            .setName(rs.getString("obj_fio"))
                            .setRegistration(rs.getObject("obj_dt_reg", LocalDateTime.class))
                            .build();

                    User author = User.Builder.createBuilder()
                            .setLogin(rs.getString("author"))
                            .setBirthday(rs.getObject("author_birthday", LocalDate.class))
                            .setName(rs.getString("author_fio"))
                            .setRegistration(rs.getObject("author_dt_reg", LocalDateTime.class))
                            .build();

                    AuditUser audit = new AuditUser(id, dtCreate, text, user, author);

                    data.add(audit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Невозможно прочитать дату и время из базы данных", e);
        }
            return data;
    }

    public static DBAuditUserStorage getInstance() {
        return instance;
    }
}
