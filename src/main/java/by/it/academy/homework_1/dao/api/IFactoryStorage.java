package by.it.academy.homework_1.dao.api;

public interface IFactoryStorage {
    IStorageUser getStorageUser();
    IStorageMessage getStorageMessage();
    IAuditUserStorage getAuditUserStorage();
}
