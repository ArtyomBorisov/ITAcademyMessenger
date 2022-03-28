package by.it.academy.homework_1.storage.hibernate.api;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;

import javax.persistence.EntityManager;

public interface IHibernateStorageUser extends IStorageUser {
    void add(User user, EntityManager manager);
}
