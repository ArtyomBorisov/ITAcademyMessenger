package by.it.academy.homework_1.storage.memory.api;

import by.it.academy.homework_1.storage.api.IAuditUserStorage;
import by.it.academy.homework_1.storage.api.IFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.storage.memory.MemoryStorageMessage;
import by.it.academy.homework_1.storage.memory.MemoryStorageUser;
import org.springframework.stereotype.Component;

@Component
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
