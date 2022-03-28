package by.it.academy.homework_1.controller.main;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main1 {
    public static void main(String[] args) {
        HibernateStorageUser storageUser = HibernateStorageUser.getInstance();

        User user = new User("login1", "pass", "fio", LocalDate.now(),  LocalDateTime.now());

        storageUser.add(user);
    }
}
