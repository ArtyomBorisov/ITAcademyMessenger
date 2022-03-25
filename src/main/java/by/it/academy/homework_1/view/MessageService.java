package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.ChoiceFactoryStorage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.view.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {
    private static final MessageService instance = new MessageService();

    private final IStorageMessage storageMessage;
    private final IStorageUser storageUser;

    private MessageService() {
        this.storageMessage = ChoiceFactoryStorage.getInstance().getStorageMessage();
        this.storageUser = ChoiceFactoryStorage.getInstance().getStorageUser();
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
        storageMessage.add(message);
    }

    @Override
    public long getCount() {
        return storageMessage.getCount();
    }

    private void validationForMessage(Message message) {
        User user = this.storageUser.get(message.getUserTo());
        if (user == null) {
            throw new IllegalArgumentException("Нет такого пользователя");
        }
    }

    public static MessageService getInstance() {
        return instance;
    }
}
