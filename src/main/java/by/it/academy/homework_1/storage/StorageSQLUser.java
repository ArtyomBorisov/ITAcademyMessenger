package by.it.academy.homework_1.storage;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StorageSQLUser implements IStorageUser {

    private static final StorageSQLUser instance = new StorageSQLUser();
    private final Connection conn;
    private final String URL = "jdbc:postgresql://localhost:5433/messenger?ApplicationName=Messenger";
    private final String USER = "postgres";
    private final String PASS = "postgres";
    private final String SQL_ERROR = "Ошибка выполнения SQL ";

    private StorageSQLUser() throws RuntimeException {
        this.conn = this.create(URL, USER, PASS);
    }

    private Connection create(String url, String user, String password){
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Проблемы с загрузкой драйвера");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(SQL_ERROR + e.getMessage());
        }

        return connection;
    }

    @Override
    public User get(String login) {
        User user = null;

        try (Statement statement = this.conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM app.users WHERE login = '" + login + "'")
        ) {
            String password = statement.executeQuery("password").toString();
            String name = statement.executeQuery("fio").toString();
            LocalDate birthday = LocalDate.parse(statement.executeQuery("birthday").toString());
            LocalDateTime registration = LocalDateTime.parse(statement.executeQuery("date_reg").toString());

            user = new User(login, password, name, birthday);
            user.setRegistration(registration);
        } catch (SQLException e) {
        }

        return user;
    }

    @Override
    public void add(User user) {
        try (PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO app.users (login, password, date_reg, fio, birthday) " +
                        "VALUES (?, ?, ?, ?, ?)");
        ) {
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
    public int getCount() {
        int res = 0;

        try (Statement statement = this.conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT count(*) FROM app.users")
        ) {
             res = statement.executeQuery("count").getInt("count");
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_ERROR + e.getMessage());
        }

        return res;
    }

    public static StorageSQLUser getInstance() {
        return instance;
    }
}
