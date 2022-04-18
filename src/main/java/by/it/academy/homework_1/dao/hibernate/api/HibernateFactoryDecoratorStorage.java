package by.it.academy.homework_1.dao.hibernate.api;

import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IFactoryStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.dao.hibernate.HibernateStorageMessage;
import by.it.academy.homework_1.dao.hibernate.HibernateStorageUserWithAuditDecorator;
import org.springframework.stereotype.Repository;

@Repository("hibernateFactoryDecoratorStorage")
public class HibernateFactoryDecoratorStorage implements IFactoryStorage {

    private IStorageUser storageUser;
    private IStorageMessage storageMessage;
    private IAuditUserStorage auditUserStorage;

    public HibernateFactoryDecoratorStorage(HibernateStorageUserWithAuditDecorator storageUser,
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
