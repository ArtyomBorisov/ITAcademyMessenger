package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;

import java.sql.Connection;
import java.util.List;

public interface IAuditUserStorage {
    Long create(AuditUser auditUser);
    Long create(AuditUser auditUser, Connection connection);
    List<AuditUser> read(Pageable pageable);
}
