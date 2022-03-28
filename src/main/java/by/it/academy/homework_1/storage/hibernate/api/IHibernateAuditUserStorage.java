package by.it.academy.homework_1.storage.hibernate.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.storage.api.IAuditUserStorage;

import javax.persistence.EntityManager;

public interface IHibernateAuditUserStorage extends IAuditUserStorage {
    Long create(AuditUser auditUser, EntityManager manager);
}
