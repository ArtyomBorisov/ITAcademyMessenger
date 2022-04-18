package by.it.academy.homework_1.dao.sql;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.sql.mapper.UserRowMapper;
import by.it.academy.homework_1.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

@Repository("dbStorageUser")
public class DBStorageUser implements IStorageUser {

    private final RowMapper<User> mapper = new UserRowMapper();
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBStorageUser(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
    }

    @Override
    public User get(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Не передан логин");
        }

        String sql = "SELECT\n" +
                "    login,\n" +
                "    PASSWORD,\n" +
                "    fio,\n" +
                "    birthday,\n" +
                "    dt_reg,\n" +
                "    dt_update\n" +
                "FROM\n" +
                "    app.\"user\"\n" +
                "WHERE\n" +
                "    login = :login;";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("login", login);
            return namedParameterJdbcTemplate.queryForObject(sql, params, mapper);
        } catch (DataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public Collection<User> getAll() {
        String sql = "SELECT\n" +
                "    login,\n" +
                "    PASSWORD,\n" +
                "    fio,\n" +
                "    birthday,\n" +
                "    dt_reg,\n" +
                "    dt_update\n" +
                "FROM\n" +
                "    app.\"user\";";

        try {
            return namedParameterJdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public User create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Не передан пользователь");
        }

        String sql = "INSERT INTO app.\"user\" (login, PASSWORD, fio, birthday, dt_reg, dt_update)\n" +
                "    VALUES (:login, :password, :fio, :birthday, :dt_reg, :dt_update);";

        try {
            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("login", user.getLogin());
            params.addValue("password", user.getPassword());
            params.addValue("fio", user.getName());
            params.addValue("birthday", user.getBirthday());
            params.addValue("dt_reg", user.getRegistration());
            params.addValue("dt_update", user.getLastUpdate());

            namedParameterJdbcTemplate.update(sql, params);
            return user;
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }

    }

    @Override
    public long getCount() {
        String sql = "SELECT count(login) FROM app.\"user\";";

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Long.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public User update(User user, String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkLoginAndLastUpdate(login, lastUpdate);
        String sql = "UPDATE\n" +
                "    app.\"user\"\n" +
                "SET\n" +
                "    login = :login,\n" +
                "    PASSWORD = :PASSWORD,\n" +
                "    fio = :fio,\n" +
                "    birthday = :birthday,\n" +
                "    dt_reg = :dt_reg,\n" +
                "    dt_update = :dt_update\n" +
                "WHERE\n" +
                "    login = :login\n" +
                "    AND dt_update = :old_dt_update;";
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("login", login);
            params.addValue("old_dt_update", lastUpdate);

            params.addValue("PASSWORD", user.getPassword());
            params.addValue("fio", user.getName());
            params.addValue("birthday", user.getBirthday());
            params.addValue("dt_update", user.getLastUpdate());

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось обновить пользователя с логином \"" + login + "\"");
            }

            return this.get(login);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
    }

    @Override
    public void delete(String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkLoginAndLastUpdate(login, lastUpdate);
        String sql = "DELETE FROM app.\"user\"\n" +
                "WHERE login = :login\n" +
                "    AND dt_update = :old_dt_update;";
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("login", login);
            params.addValue("old_dt_update", lastUpdate);

            int update = namedParameterJdbcTemplate.update(sql, params);

            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить пользователя с логином \"" + login + "\"");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }

    }

    private boolean checkLoginAndLastUpdate(String login, LocalDateTime lastUpdate) {
        if (login == null) {
            throw new IllegalArgumentException("Не передан логин");
        }
        if (lastUpdate == null) {
            throw new IllegalArgumentException("Не передан lastUpdate");
        }

        return true;
    }
}
