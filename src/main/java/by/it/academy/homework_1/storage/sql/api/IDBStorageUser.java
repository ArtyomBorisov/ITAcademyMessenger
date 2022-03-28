package by.it.academy.homework_1.storage.sql.api;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;

import java.sql.Connection;

public interface IDBStorageUser extends IStorageUser {
    void add(User user, Connection conn);
}
