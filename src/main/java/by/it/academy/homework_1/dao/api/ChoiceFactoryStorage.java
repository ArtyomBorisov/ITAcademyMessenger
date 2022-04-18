package by.it.academy.homework_1.dao.api;

import java.util.Map;

public class ChoiceFactoryStorage implements IFactoryStorage {

    private IFactoryStorage factoryStorage;

    public ChoiceFactoryStorage(String choiceType,
                                Map<String, IFactoryStorage> storages) {
        this.factoryStorage = storages.get(choiceType);

        if (this.factoryStorage == null) {
            throw new IllegalArgumentException("Неизвестный тип Storage: " + choiceType);
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
