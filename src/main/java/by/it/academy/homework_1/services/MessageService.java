package by.it.academy.homework_1.services;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.services.api.IMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessageService implements IMessageService {

    private final IStorageMessage storageMessage;

    public MessageService(IStorageMessage storageMessage) {
        this.storageMessage = storageMessage;
    }

    @Override
    public List<Message> get(User currentUser) {
        return storageMessage.get(currentUser.getLogin());
    }

    @Override
    public List<Message> get(String currentLoginUser) {
        return storageMessage.get(currentLoginUser);
    }

    @Override
    @Transactional
    public void add(Message message) {
        this.validationForMessage(message);
        LocalDateTime now = LocalDateTime.now();
        message.setTimeSending(now);
        message.setLastUpdate(now);
        storageMessage.create(message);
    }

    @Override
    public long getCount() {
        return storageMessage.getCount();
    }

    @Override
    public Message update(Message message, Long id, LocalDateTime lastUpdate) {
        message.setLastUpdate(LocalDateTime.now());
        return this.storageMessage.update(message, id, lastUpdate);
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) {
        this.storageMessage.delete(id, lastUpdate);
    }

    private void validationForMessage(Message message) {
        if (message.getUserTo() == null) {
            throw new IllegalArgumentException("Нет такого пользователя");
        }
    }
}
