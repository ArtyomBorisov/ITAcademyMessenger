package by.it.academy.homework_1.storage.sql;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.sql.api.IDBStorageUser;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DBStorageUser implements IDBStorageUser {

    private final DataSource dataSource;

    public DBStorageUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User get(String login) {
        User user = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT\n" +
                     "    login,\n" +
                     "    \"password\",\n" +
                     "    date_trunc('seconds', date_reg),\n" +
                     "    fio,\n" +
                     "    birthday\n" +
                     "FROM\n" +
                     "    app.users\n" +
                     "WHERE\n" +
                     "    login ='" +
                     login + "'"))
        {
            while (rs.next()) {
                String password = rs.getString(2);
                LocalDateTime registration = LocalDateTime.parse(rs.getString(3), formatter);
                String name = rs.getString(4);
                LocalDate birthday = LocalDate.parse(rs.getString(5));

                user = new User(login, password, name, birthday);
                user.setRegistration(registration);
            }
        } catch (NullPointerException e) {
            throw new IllegalStateException("Отсутвует дата рождения или дата регистрации в базе данных", e);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Невозможно преобразовать дату рождения или дату регистрации в " +
                    "требуемый формат. ", e);
        } catch (SQLException e) {}

        return user;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {
            this.add(user, conn);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД");
        }
    }

    @Override
    public void add(User user, Connection conn) {
        if(conn == null){
            add(user);
        }
        if(user == null){
            throw new IllegalArgumentException("User не может быть null");
        }

        try (PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO app.users (login, \"password\", date_reg, fio, birthday) " +
                             "VALUES (?, ?, ?, ?, ?);"))
        {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setObject(3, user.getRegistration());
            statement.setString(4, user.getName());
            statement.setObject(5, user.getBirthday());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }
    }

    @Override
    public long getCount() {
        long res = 0;

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT count(*) FROM app.users"))
        {
            while (rs.next()) {
                res = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL " + e.getMessage());
        }

        return res;
    }
}
