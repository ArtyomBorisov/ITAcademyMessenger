package by.it.academy.homework_1.controller.main;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageMessage;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUserWithAuditDecorator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main3 {
    public static void main(String[] args) {
        //HibernateStorageUser storageUser = HibernateStorageUser.getInstance();
        HibernateAuditUserStorage auditUserStorage = HibernateAuditUserStorage.getInstance();
        //HibernateStorageMessage storageMessage = HibernateStorageMessage.getInstance();
        HibernateStorageUserWithAuditDecorator decorator = HibernateStorageUserWithAuditDecorator.getInstance();

        User user = new User("user1", "pass", "fio", LocalDate.now(),  LocalDateTime.now());
        //Message message = new Message("login", "login", "text", LocalDateTime.now());
        AuditUser auditUser = new AuditUser(LocalDateTime.now(), "text", user, null);

        //storageUser.add(user);
        //storageMessage.add(message);
        //auditUserStorage.create(auditUser);
        decorator.add(user);
    }
}
