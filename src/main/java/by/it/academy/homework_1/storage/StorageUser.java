package by.it.academy.homework_1.storage;

import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.model.User;

import java.util.HashMap;
import java.util.Map;

public class StorageUser implements IStorageUser {

    private static final IStorageUser instance = new StorageUser();
    private final Map<String, User> storageUsers;

    private StorageUser() {
        this.storageUsers = new HashMap<>();
    }

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

    public static IStorageUser getInstance() {
        return instance;
    }
}
