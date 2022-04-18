package by.it.academy.homework_1.dao.memory;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository("memoryStorageUser")
public class MemoryStorageUser implements IStorageUser {

    private final Map<String, User> storageUsers = new HashMap<>();

    @Override
    public User get(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Не передан логин");
        }
        return this.storageUsers.get(login);
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<User>(this.storageUsers.values());
    }

    @Override
    public long getCount() {
        return this.storageUsers.size();
    }

    @Override
    public User create(User user) {
        if (!storageUsers.containsKey(user.getLogin())) {
            storageUsers.put(user.getLogin(), user);
        } else {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }

        return this.storageUsers.get(user.getLogin());
    }

    @Override
    public User update(User user, String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkLoginAndLastUpdate(login, lastUpdate);

        user.setLogin(login);
        this.storageUsers.put(login, user);

        return this.storageUsers.get(login);
    }

    @Override
    public void delete(String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        this.checkLoginAndLastUpdate(login, lastUpdate);

        this.storageUsers.remove(login);
    }

    private boolean checkLoginAndLastUpdate(String login, LocalDateTime lastUpdate) {
        if (login == null) {
            throw new IllegalArgumentException("Не передан логин");
        }
        if (lastUpdate == null) {
            throw new IllegalArgumentException("Не передан lastUpdate");
        }

        User foundUser = storageUsers.get(login);

        if (foundUser == null) {
            throw new EssenceNotFound("Пользователь с логином " + login + " не существует");
        }
        if (lastUpdate.compareTo(foundUser.getLastUpdate()) != 0) {
            throw new EssenceNotFound("Пользователь с логином " + login +
                    " и датой обновления " + lastUpdate + " не существует");
        }
        return true;
    }
}
