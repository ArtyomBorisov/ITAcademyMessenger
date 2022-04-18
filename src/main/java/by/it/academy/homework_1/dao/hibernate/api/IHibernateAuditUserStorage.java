package by.it.academy.homework_1.dao.hibernate.api;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.dao.api.IAuditUserStorage;

import javax.persistence.EntityManager;

public interface IHibernateAuditUserStorage extends IAuditUserStorage {
    AuditUser create(AuditUser auditUser, EntityManager manager);
}
