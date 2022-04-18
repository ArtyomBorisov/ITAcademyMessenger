package by.it.academy.homework_1.dao.sql;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.sql.mapper.AuditUserRowMapper;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("dbAuditUserStorage")
public class DBAuditUserStorage implements IAuditUserStorage {

    private final RowMapper<AuditUser> mapper = new AuditUserRowMapper();
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBAuditUserStorage(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Override
    public AuditUser create(AuditUser auditUser) {
        if(auditUser == null){
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO app.audit_user (dt_create, text, \"user\", author, dt_update)\n" +
                "    VALUES (:dt_create, :text, :user, :author, :dt_update);";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("dt_create", auditUser.getDtCreate());
            params.addValue("text", auditUser.getText());
            params.addValue("user", auditUser.getUser() == null ? null : auditUser.getUser().getLogin());
            params.addValue("author",
                    auditUser.getAuthor() == null ? null : auditUser.getAuthor().getLogin());
            params.addValue("dt_update", auditUser.getLastUpdate());

            namedParameterJdbcTemplate.update(sql, params);
            return auditUser;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public AuditUser update(AuditUser auditUser, Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkIdAndLastUpdate(id, lastUpdate);

        String sql = "UPDATE\n" +
                "    app.audit_user\n" +
                "SET\n" +
                "    text = :text,\n" +
                "    \"user\" = :user,\n" +
                "    author = :author,\n" +
                "    dt_update = :dt_update\n" +
                "WHERE\n" +
                "    id = :id\n" +
                "    AND dt_update = :old_dt_update;";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("text", auditUser.getText());
            params.addValue("user", auditUser.getUser().getLogin());
            params.addValue("author", auditUser.getAuthor().getLogin());
            params.addValue("dt_update", auditUser.getLastUpdate());
            params.addValue("id", id);
            params.addValue("old_dt_update", lastUpdate);

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось обновить аудит с id " + id);
            }

            return auditUser;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkIdAndLastUpdate(id, lastUpdate);

        String sql = "DELETE FROM app.audit_user\n" +
                "WHERE id = :id\n" +
                "    AND dt_update = :old_dt_update;";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("id", id);
            params.addValue("old_dt_update", lastUpdate);

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить аудит с id " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
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
                "    audit.id,\n" +
                "    audit.dt_create,\n" +
                "    audit.text,\n" +
                "    audit.user,\n" +
                "    audit.author,\n" +
                "    audit.dt_update,\n" +
                "    user_obj.fio AS user_fio,\n" +
                "    user_obj.birthday AS user_birthday,\n" +
                "    user_obj.dt_reg AS user_dt_reg,\n" +
                "    user_obj.dt_update AS user_dt_update,\n" +
                "    author.fio AS author_fio,\n" +
                "    author.birthday AS author_birthday,\n" +
                "    author.dt_reg AS author_dt_reg,\n" +
                "    author.dt_update AS author_dt_update\n" +
                "FROM\n" +
                "    app.audit_user AS audit\n" +
                "    LEFT JOIN app.user AS user_obj ON audit.user = user_obj.login\n" +
                "    LEFT JOIN app.user AS author ON audit.author = author.login";

        if (limit != null) {
            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try {
            return namedParameterJdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL ", e);
        }
    }

    private boolean checkIdAndLastUpdate(Long id, LocalDateTime lastUpdate) {
        if (id == null) {
            throw new IllegalArgumentException("Не передан id");
        }
        if (lastUpdate == null) {
            throw new IllegalArgumentException("Не передан lastUpdate");
        }

        return true;
    }
}
