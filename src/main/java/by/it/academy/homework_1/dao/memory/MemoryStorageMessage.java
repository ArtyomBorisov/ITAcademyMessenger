package by.it.academy.homework_1.dao.memory;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.model.Message;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository("memoryStorageMessage")
public class MemoryStorageMessage implements IStorageMessage {

    private final Map<String, List <Message>> storageMessages = new HashMap<>();
    private final AtomicLong counterId;

    public MemoryStorageMessage() {
        this.counterId = new AtomicLong(0);
    }

    @Override
    public List<Message> get(String loginUser) {
        return this.storageMessages.get(loginUser);
    }

    @Override
    public Message create(Message message) {
        String loginUserTo = message.getUserTo().getLogin();

        if (!this.storageMessages.containsKey(loginUserTo)) {
            this.storageMessages.put(loginUserTo, new ArrayList<>());
        }

        this.storageMessages.get(loginUserTo).add(message);
        message.setId(counterId.incrementAndGet());

        return message;
    }

    @Override
    public long getCount() {
        long res = 0;
        for (List<Message> value : this.storageMessages.values()) {
            res += value.size();
        }
        return res;
    }

    @Override
    public Message update(Message message, Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.delete(id, lastUpdate);
        message.setId(id);
        this.create(message);
        return message;
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        if (id == null) {
            throw new IllegalArgumentException("Не передан id");
        }
        if (lastUpdate == null) {
            throw new IllegalArgumentException("Не передан lastUpdate");
        }

        for (List<Message> value : this.storageMessages.values()) {
            for (Message foundMessage : value) {
                if (id.compareTo(foundMessage.getId()) == 0) {
                    if (foundMessage.getLastUpdate().compareTo(lastUpdate) != 0) {
                        throw new EssenceNotFound("Сообщения с id " + id +
                                " и датой обновления " + lastUpdate + " не существует");
                    }
                    value.remove(foundMessage);
                    return;
                }
            }
        }

        throw new EssenceNotFound("Сообщения с id " + id + " не существует");
    }
}
