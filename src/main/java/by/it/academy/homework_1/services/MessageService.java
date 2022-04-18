package by.it.academy.homework_1.services;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.services.api.IMessageService;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    private final IStorageMessage storageMessage;
    private final IUserService userService;

    public MessageService(IStorageMessage storageMessage,
                          IUserService userService) {
        this.storageMessage = storageMessage;
        this.userService = userService;
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

    private void validationForMessage(Message message) {
        if (message.getUserTo() == null) {
            throw new IllegalArgumentException("Нет такого пользователя");
        }
    }
}
