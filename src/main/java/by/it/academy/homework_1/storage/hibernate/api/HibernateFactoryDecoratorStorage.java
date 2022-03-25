package by.it.academy.homework_1.storage.hibernate.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageMessage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUserWithAuditDecorator;

public class HibernateFactoryDecoratorStorage implements IFactoryStorage {
    @Override
    public IStorageUser getStorageUser() {
        return HibernateStorageUserWithAuditDecorator.getInstance();
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return HibernateStorageMessage.getInstance();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return HibernateAuditUserStorage.getInstance();
    }
}
