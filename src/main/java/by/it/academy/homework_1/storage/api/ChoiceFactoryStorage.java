package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.storage.hibernate.api.HibernateFactoryDecoratorStorage;

public class ChoiceFactoryStorage implements IFactoryStorage {
    private static final ChoiceFactoryStorage instance = new ChoiceFactoryStorage();

    private IFactoryStorage fs = new HibernateFactoryDecoratorStorage();

    @Override
    public IStorageUser getStorageUser() {
        return fs.getStorageUser();
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return fs.getStorageMessage();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return fs.getAuditUserStorage();
    }

    public static ChoiceFactoryStorage getInstance() {
        return instance;
    }
}
