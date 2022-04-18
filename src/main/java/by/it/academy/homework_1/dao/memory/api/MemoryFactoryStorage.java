package by.it.academy.homework_1.dao.memory.api;

import by.it.academy.homework_1.dao.api.IAuditUserStorage;
import by.it.academy.homework_1.dao.api.IFactoryStorage;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.dao.memory.MemoryStorageMessage;
import by.it.academy.homework_1.dao.memory.MemoryStorageUser;
import org.springframework.stereotype.Repository;

@Repository("memoryFactoryStorage")
public class MemoryFactoryStorage implements IFactoryStorage {

    private IStorageUser storageUser;
    private IStorageMessage storageMessage;

    public MemoryFactoryStorage(MemoryStorageUser memoryStorageUser,
                                MemoryStorageMessage memoryStorageMessage) {
        this.storageUser = memoryStorageUser;
        this.storageMessage = memoryStorageMessage;
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
        return null;
    }
}
