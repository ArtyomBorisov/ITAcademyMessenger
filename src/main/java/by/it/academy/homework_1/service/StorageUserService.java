package by.it.academy.homework_1.service;

import by.it.academy.homework_1.service.api.IStorageUserService;
import by.it.academy.homework_1.service.dto.User;

import java.util.HashMap;
import java.util.Map;

public class StorageUserService implements IStorageUserService {

    private static final IStorageUserService instance = new StorageUserService();
    private final Map<String, User> storageUsers;

    private StorageUserService() {
        this.storageUsers = new HashMap<>();
    }

    @Override
    public User getUser(String login) {
        return storageUsers.get(login);
    }

    @Override
    public void addUserToStorage(User user) {
        if (!storageUsers.containsKey(user.getLogin())) {
            storageUsers.put(user.getLogin(), user);
        }
    }

    @Override
    public boolean isLoginExist(String login) {
        return storageUsers.containsKey(login);
    }

    @Override
    public boolean authenticationUser(String login, String password) {

        if (storageUsers.containsKey(login)) {
            return storageUsers.get(login).getPassword().equals(password);
        }

        return false;
    }

    public static IStorageUserService getInstance() {
        return instance;
    }
}
