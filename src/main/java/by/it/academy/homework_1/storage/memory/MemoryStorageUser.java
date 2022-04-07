package by.it.academy.homework_1.storage.memory;

import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryStorageUser implements IStorageUser {

    private final Map<String, User> storageUsers = new HashMap<>();

    @Override
    public User get(String login) {
        return storageUsers.get(login);
    }

    @Override
    public void add(User user) {
        if (!storageUsers.containsKey(user.getLogin())) {
            storageUsers.put(user.getLogin(), user);
        } else {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }
    }

    @Override
    public long getCount() {
        return storageUsers.size();
    }
}
