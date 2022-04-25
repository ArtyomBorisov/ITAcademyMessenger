package by.it.academy.homework_1.dao.sql.mapper;

import by.it.academy.homework_1.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.Builder.createBuilder()
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .setName(rs.getString("fio"))
                .setBirthday(rs.getObject("birthday", LocalDate.class))
                .setRegistration(rs.getObject("dt_reg", LocalDateTime.class))
                .setLastUpdate(rs.getObject("dt_update", LocalDateTime.class))
                .setAccessible(rs.getBoolean("accessible"))
                .build();
    }
}
