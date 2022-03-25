package by.it.academy.homework_1.storage.sql.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.sql.DBAuditUserStorage;
import by.it.academy.homework_1.storage.sql.DBStorageMessage;
import by.it.academy.homework_1.storage.sql.DBStorageUser;

public class SQLFactoryStorage implements IFactoryStorage {
    @Override
    public IStorageUser getStorageUser() {
        return DBStorageUser.getInstance();
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return DBStorageMessage.getInstance();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return DBAuditUserStorage.getInstance();
    }
}
