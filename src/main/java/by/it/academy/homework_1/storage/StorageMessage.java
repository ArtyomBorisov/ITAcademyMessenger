package by.it.academy.homework_1.storage;

import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageMessage implements IStorageMessage {

    private static final StorageMessage instance = new StorageMessage();
    private final Map<String, List <Message>> storageMessages;

    private StorageMessage() {
        this.storageMessages = new HashMap<>();
    }

    @Override
    public List<Message> get(String loginUser) {
        return storageMessages.get(loginUser);
    }

    @Override
    public void add(Message message) {
        String loginUserTo = message.getUserTo();

        if (!storageMessages.containsKey(loginUserTo)) {
            storageMessages.put(loginUserTo, new ArrayList<>());
        }

        storageMessages.get(loginUserTo).add(message);
    }

    @Override
    public int getCount() {
        int res = 0;
        for (List<Message> value : storageMessages.values()) {
            res += value.size();
        }
        return res;
    }

    public static StorageMessage getInstance() {
        return instance;
    }
}
