package by.it.academy.homework_1.dao.sql.mapper;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuditUserRowMapper implements RowMapper<AuditUser> {
    @Override
    public AuditUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuditUser auditUser = new AuditUser();

        auditUser.setId(rs.getLong("id"));
        auditUser.setDtCreate(rs.getObject("dt_create", LocalDateTime.class));
        auditUser.setText(rs.getString("text"));
        auditUser.setLastUpdate(rs.getObject("dt_update", LocalDateTime.class));

        User user = User.Builder.createBuilder()
                .setLogin(rs.getString("user"))
                .setName(rs.getString("user_fio"))
                .setBirthday(rs.getObject("user_birthday", LocalDate.class))
                .setRegistration(rs.getObject("user_dt_reg", LocalDateTime.class))
                .setLastUpdate(rs.getObject("user_dt_update", LocalDateTime.class))
                .build();

        User author = User.Builder.createBuilder()
                .setLogin(rs.getString("author"))
                .setName(rs.getString("author_fio"))
                .setBirthday(rs.getObject("author_birthday", LocalDate.class))
                .setRegistration(rs.getObject("author_dt_reg", LocalDateTime.class))
                .setLastUpdate(rs.getObject("author_dt_update", LocalDateTime.class))
                .build();

        auditUser.setUser(user);
        auditUser.setAuthor(author);

        return auditUser;
    }
}
