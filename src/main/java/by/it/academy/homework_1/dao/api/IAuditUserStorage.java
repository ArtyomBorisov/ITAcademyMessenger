package by.it.academy.homework_1.dao.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;

import java.util.List;

public interface IAuditUserStorage extends ICUDRepository<AuditUser, Long> {
    List<AuditUser> read(Pageable pageable);
}
