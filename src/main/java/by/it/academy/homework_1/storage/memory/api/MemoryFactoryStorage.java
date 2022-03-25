package by.it.academy.homework_1.storage.memory.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.memory.StorageMessage;
import by.it.academy.homework_1.storage.memory.StorageUser;

public class MemoryFactoryStorage implements IFactoryStorage {
    @Override
    public IStorageUser getStorageUser() {
        return StorageUser.getInstance();
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return StorageMessage.getInstance();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return null;
    }
}
