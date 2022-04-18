package by.it.academy.homework_1.dao.sql.api;

import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IFactoryStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.sql.DBAuditUserStorage;
import by.it.academy.homework_1.dao.sql.DBStorageMessage;
import by.it.academy.homework_1.dao.sql.DBStorageUser;
import org.springframework.stereotype.Repository;

@Repository("sqlFactoryStorage")
public class SQLFactoryStorage implements IFactoryStorage {

    private IStorageUser storageUser;
    private IStorageMessage storageMessage;
    private IAuditUserStorage auditUserStorage;

    public SQLFactoryStorage(DBStorageUser storageUser,
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
