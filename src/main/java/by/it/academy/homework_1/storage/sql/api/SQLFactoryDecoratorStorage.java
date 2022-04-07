package by.it.academy.homework_1.storage.sql.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.sql.DBAuditUserStorage;
import by.it.academy.homework_1.storage.sql.DBStorageMessage;
import by.it.academy.homework_1.storage.sql.DBStorageUserWithAuditDecorator;
import org.springframework.stereotype.Component;

@Component
public class SQLFactoryDecoratorStorage implements IFactoryStorage {

    private IStorageUser storageUser;
    private IStorageMessage storageMessage;
    private IAuditUserStorage auditUserStorage;

    public SQLFactoryDecoratorStorage(DBStorageUserWithAuditDecorator storageUser,
                                      DBStorageMessage storageMessage,
                                      DBAuditUserStorage auditUserStorage) {
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
