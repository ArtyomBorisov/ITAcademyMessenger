package by.it.academy.homework_1.storage.hibernate.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageMessage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;
import org.springframework.stereotype.Component;

@Component
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
