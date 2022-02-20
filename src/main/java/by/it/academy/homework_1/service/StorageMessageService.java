package by.it.academy.homework_1.service;

import by.it.academy.homework_1.service.api.IStorageMessageService;
import by.it.academy.homework_1.service.dto.Message;
import by.it.academy.homework_1.service.dto.SavedMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageMessageService implements IStorageMessageService {

    private static final StorageMessageService instance = new StorageMessageService();
    private final Map<String, List <SavedMessage>> storageMessages;

    private StorageMessageService() {
        this.storageMessages = new HashMap<>();
    }

    @Override
    public List<SavedMessage> getMessagesToUser(String loginUser) {
        return storageMessages.get(loginUser);
    }

    @Override
    public void addToStorage(Message message) {
        String loginUserTo = message.getUserTo();

        if (!storageMessages.containsKey(loginUserTo)) {
            storageMessages.put(loginUserTo, new ArrayList<>());
        }

        storageMessages.get(loginUserTo).add(new SavedMessage(LocalDateTime.now(), message));
    }

    @Override
    public int getCountMessages() {
        int res = 0;
        for (List<SavedMessage> value : storageMessages.values()) {
            res += value.size();
        }
        return res;
    }

    public static StorageMessageService getInstance() {
        return instance;
    }
}
