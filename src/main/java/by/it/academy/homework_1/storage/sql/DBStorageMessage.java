package by.it.academy.homework_1.storage.sql;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.sql.api.SQLDBInitializer;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DBStorageMessage implements IStorageMessage {

    private static final DBStorageMessage instance = new DBStorageMessage();
    private final DataSource dataSource;

    private DBStorageMessage() {
        this.dataSource = SQLDBInitializer.getInstance().getDataSource();
    }

    @Override
    public List<Message> get(String loginUser) {
        List<Message> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT\n" +
                     "    \"from\",\n" +
                     "    date_trunc('seconds', send_date),\n" +
                     "    text\n" +
                     "FROM\n" +
                     "    app.messages\n" +
                     "WHERE\n" +
                     "    \"to\" = '" +
                     loginUser + "'"))
        {
            while (rs.next()) {
                String from = rs.getString(1);

                LocalDateTime sendDate = null;
                String sendDateRaw = rs.getString(2);
                if (!rs.wasNull()) {
                    sendDate = LocalDateTime.parse(sendDateRaw, formatter);
                }

                String text = rs.getString(3);

                list.add(new Message(from, loginUser, text, sendDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL " + e.getMessage());
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Невозможно преобразовать дату отправки сообщения в требуемый формат", e);
        }
        return list;
    }

    @Override
    public void add(Message message) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO app.messages (\"from\", \"to\", send_date, text) " +
                             "VALUES (?, ?, ?, ?)")) {
            statement.setString(1, message.getUserFrom());
            statement.setString(2, message.getUserTo());
            statement.setObject(3, message.getTimeSending());
            statement.setString(4, message.getTextMessage());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    @Override
    public long getCount() {
        long res = 0;

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT count(*) FROM app.messages"))
        {
            while (rs.next()) {
                res = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL " + e.getMessage());
        }

        return res;
    }

    public static DBStorageMessage getInstance() {
        return instance;
    }
}
