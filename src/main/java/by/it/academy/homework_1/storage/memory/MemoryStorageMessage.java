package by.it.academy.homework_1.storage.memory;

import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.model.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemoryStorageMessage implements IStorageMessage {

    private final Map<String, List <Message>> storageMessages = new HashMap<>();

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
    public long getCount() {
        long res = 0;
        for (List<Message> value : storageMessages.values()) {
            res += value.size();
        }
        return res;
    }
}