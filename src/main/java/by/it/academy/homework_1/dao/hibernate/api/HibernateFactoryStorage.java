package by.it.academy.homework_1.dao.hibernate.api;

import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IFactoryStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.dao.hibernate.HibernateStorageMessage;
import by.it.academy.homework_1.dao.hibernate.HibernateStorageUser;
import org.springframework.stereotype.Repository;

@Repository("hibernateFactoryStorage")
public class HibernateFactoryStorage implements IFactoryStorage {

    private IStorageUser storageUser;
    private IStorageMessage storageMessage;
    private IAuditUserStorage auditUserStorage;

    public HibernateFactoryStorage(HibernateStorageUser storageUser,
                                   HibernateStorageMessage storageMessage,
                                   HibernateAuditUserStorage auditUserStorage) {
        this.storageUser = storageUser;
        this.storageMessage = storageMessage;
        this.auditUserStorage = auditUserStorage;
    }

    @Override
    public IStorageUser getStorageUser() {
        return this.storageUser;
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return this.storageMessage;
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return this.auditUserStorage;
    }
}
