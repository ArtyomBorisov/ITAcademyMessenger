package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.DBStorageMessage;
import by.it.academy.homework_1.storage.HibernateStorageMessage;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.view.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {
    private static final MessageService instance = new MessageService();

    private final IStorageMessage storageMessage;

    private MessageService() {
        this.storageMessage = HibernateStorageMessage.getInstance();
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
        storageMessage.add(message);
    }

    @Override
    public long getCount() {
        return storageMessage.getCount();
    }

    public static MessageService getInstance() {
        return instance;
    }
}
