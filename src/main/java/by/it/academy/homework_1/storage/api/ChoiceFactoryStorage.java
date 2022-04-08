package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.storage.hibernate.api.HibernateFactoryDecoratorStorage;
import by.it.academy.homework_1.storage.hibernate.api.HibernateFactoryStorage;
import by.it.academy.homework_1.storage.memory.api.MemoryFactoryStorage;
import by.it.academy.homework_1.storage.sql.api.SQLFactoryDecoratorStorage;
import by.it.academy.homework_1.storage.sql.api.SQLFactoryStorage;
import org.springframework.stereotype.Component;

@Component
public class ChoiceFactoryStorage implements IFactoryStorage {

    private IFactoryStorage factoryStorage;

    public ChoiceFactoryStorage(String type,
                                MemoryFactoryStorage memoryFactoryStorage,
                                SQLFactoryStorage sqlFactoryStorage,
                                SQLFactoryDecoratorStorage sqlFactoryDecoratorStorage,
                                HibernateFactoryStorage hibernateFactoryStorage,
                                HibernateFactoryDecoratorStorage hibernateFactoryDecoratorStorage) {
        switch (type.toLowerCase()) {
            case "memory":
                this.factoryStorage = memoryFactoryStorage;
                break;
            case "sql":
                this.factoryStorage = sqlFactoryStorage;
                break;
            case "sql_with_audit":
                this.factoryStorage = sqlFactoryDecoratorStorage;
                break;
            case "hibernate":
                this.factoryStorage = hibernateFactoryStorage;
                break;
            case "hibernate_with_audit":
                this.factoryStorage = hibernateFactoryDecoratorStorage;
                break;
            default:
                throw new RuntimeException("Неизвестный тип Storage: " + type);
        }
    }

    @Override
    public IStorageUser getStorageUser() {
        return this.factoryStorage.getStorageUser();
    }

    @Override
    public IStorageMessage getStorageMessage() {
        return this.factoryStorage.getStorageMessage();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return this.factoryStorage.getAuditUserStorage();
    }
}
