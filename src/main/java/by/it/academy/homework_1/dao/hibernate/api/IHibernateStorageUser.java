package by.it.academy.homework_1.dao.hibernate.api;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageUser;

import javax.persistence.EntityManager;

public interface IHibernateStorageUser extends IStorageUser {
    User create(User user, EntityManager manager);
}
