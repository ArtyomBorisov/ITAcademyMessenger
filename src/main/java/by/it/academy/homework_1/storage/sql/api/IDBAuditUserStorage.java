package by.it.academy.homework_1.storage.sql.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.storage.api.IAuditUserStorage;

import java.sql.Connection;

public interface IDBAuditUserStorage extends IAuditUserStorage {
    Long create(AuditUser auditUser, Connection conn);
}
