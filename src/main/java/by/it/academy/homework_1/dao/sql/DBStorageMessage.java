package by.it.academy.homework_1.dao.sql;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.sql.mapper.MessageRowMapper;
import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Repository("dbStorageMessage")
public class DBStorageMessage implements IStorageMessage {

    private final RowMapper<Message> mapper = new MessageRowMapper();
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBStorageMessage(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Override
    public List<Message> get(String loginUser) {
        if (loginUser == null) {
            throw new IllegalArgumentException("Не передан логин");
        }

        String sql = "SELECT\n" +
                "    message.id,\n" +
                "    message.from,\n" +
                "    message.to,\n" +
                "    message.text,\n" +
                "    message.send_dt,\n" +
                "    message.dt_update,\n" +
                "    user_from.fio AS from_fio,\n" +
                "    user_from.birthday AS from_birthday,\n" +
                "    user_from.dt_reg AS from_dt_reg,\n" +
                "    user_from.dt_update AS from_dt_update,\n" +
                "    user_to.fio AS to_fio,\n" +
                "    user_to.birthday AS to_birthday,\n" +
                "    user_to.dt_reg AS to_dt_reg,\n" +
                "    user_to.dt_update AS to_dt_update\n" +
                "FROM\n" +
                "    app.message\n" +
                "    LEFT JOIN app.user AS user_from ON message.from = user_from.login\n" +
                "    LEFT JOIN app.user AS user_to ON message.to = user_to.login\n" +
                "WHERE\n" +
                "    message.to = :loginUser;";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("loginUser", loginUser);
            return namedParameterJdbcTemplate.query(sql, params, mapper);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL ", e);
        }
    }

    @Override
    public Message create(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Не передано сообщение");
        }

        String sql = "INSERT INTO app.message (\"from\", \"to\", text, send_dt, dt_update)\n" +
                "    VALUES (:from, :to, :text, :send_dt, :dt_update);";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("from", message.getUserFrom().getLogin());
            params.addValue("to", message.getUserTo().getLogin());
            params.addValue("text", message.getTextMessage());
            params.addValue("send_dt", message.getTimeSending());
            params.addValue("dt_update", message.getLastUpdate());

            namedParameterJdbcTemplate.update(sql, params);
            return message;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public long getCount() {
        String sql = "SELECT count(id) FROM app.message;";

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Long.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public Message update(Message message, Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkIdAndLastUpdate(id, lastUpdate);

        String sql = "UPDATE\n" +
                "    app.message\n" +
                "SET\n" +
                "    \"from\" = :from,\n" +
                "    \"to\" = :to,\n" +
                "    text = :text,\n" +
                "    dt_update = :dt_update\n" +
                "WHERE\n" +
                "    id = :id\n" +
                "    AND dt_update = :old_dt_update;";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("id", id);
            params.addValue("from", message.getUserFrom().getLogin());
            params.addValue("to", message.getUserTo().getLogin());
            params.addValue("text", message.getTextMessage());
            params.addValue("dt_update", message.getLastUpdate());
            params.addValue("old_dt_update", lastUpdate);

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось обновить сообщение с id " + id);
            }

            return message;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkIdAndLastUpdate(id, lastUpdate);
        String sql = "DELETE FROM app.message\n" +
                "WHERE id = :id\n" +
                "    AND dt_update = :old_dt_update;";
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);
            params.addValue("old_dt_update", lastUpdate);

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить сообщение с id " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
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
