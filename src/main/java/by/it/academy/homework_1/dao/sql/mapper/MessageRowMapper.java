package by.it.academy.homework_1.dao.sql.mapper;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String text = rs.getString("text");
        LocalDateTime sendDt = rs.getObject("send_dt", LocalDateTime.class);
        LocalDateTime dtUpdate = rs.getObject("dt_update", LocalDateTime.class);

        User from = User.Builder.createBuilder()
                .setLogin(rs.getString("from"))
                .setName(rs.getString("from_fio"))
                .setBirthday(rs.getObject("from_birthday", LocalDate.class))
                .setRegistration(rs.getObject("from_dt_reg", LocalDateTime.class))
                .setLastUpdate(rs.getObject("from_dt_update", LocalDateTime.class))
                .build();

        User to = User.Builder.createBuilder()
                .setLogin(rs.getString("to"))
                .setName(rs.getString("to_fio"))
                .setBirthday(rs.getObject("to_birthday", LocalDate.class))
                .setRegistration(rs.getObject("to_dt_reg", LocalDateTime.class))
                .setLastUpdate(rs.getObject("to_dt_update", LocalDateTime.class))
                .build();

        return new Message(id, from, to, text, sendDt, dtUpdate);
    }
}
