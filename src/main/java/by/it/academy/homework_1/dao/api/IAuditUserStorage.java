package by.it.academy.homework_1.dao.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;

import java.util.List;

public interface IAuditUserStorage extends ICUDRepository<AuditUser, Long> {
    List<AuditUser> read(Pageable pageable);
    List<AuditUser> read();
    List<AuditUser> read(String login);
    List<AuditUser> read(String login, Pageable pageable);
}
