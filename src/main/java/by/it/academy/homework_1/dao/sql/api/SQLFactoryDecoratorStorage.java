package by.it.academy.homework_1.dao.sql.api;

import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IFactoryStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.sql.DBAuditUserStorage;
import by.it.academy.homework_1.dao.sql.DBStorageMessage;
import by.it.academy.homework_1.dao.sql.DBStorageUserWithAuditDecorator;
import org.springframework.stereotype.Repository;

@Repository("sqlFactoryDecoratorStorage")
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
