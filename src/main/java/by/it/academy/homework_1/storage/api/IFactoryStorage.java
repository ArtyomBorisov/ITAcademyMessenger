package by.it.academy.homework_1.storage.api;

public interface IFactoryStorage {
    IStorageUser getStorageUser();
    IStorageMessage getStorageMessage();
    IAuditUserStorage getAuditUserStorage();
}
