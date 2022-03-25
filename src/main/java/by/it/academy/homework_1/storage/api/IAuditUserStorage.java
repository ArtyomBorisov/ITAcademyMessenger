package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;

import java.util.List;

public interface IAuditUserStorage {
    Long create(AuditUser auditUser);
    List<AuditUser> read(Pageable pageable);
}
