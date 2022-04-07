package by.it.academy.homework_1.controller.main;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUserWithAuditDecorator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main2 {
    public static void main(String[] args) {

//        HibernateStorageUserWithAuditDecorator storageUser = HibernateStorageUserWithAuditDecorator.getInstance();

        User user = new User("login5gf", "pass", "fio", LocalDate.now(),  LocalDateTime.now());

//        storageUser.add(user);
    }
}
