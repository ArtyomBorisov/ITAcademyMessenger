package by.it.academy.homework_1.dao.api;

import by.it.academy.homework_1.model.User;

import java.util.Collection;

public interface IStorageUser extends ICUDRepository<User, String> {

    /**
     * Получение объекта User по его логину
     * @param login - логин
     * @return User - если такой объект имеется в хранилище
     *         null - если такого объекта нет в хранилище
     */
    User get(String login);

    /**
     * Получение списка всех зарегистрированных пользователей
     * @return список
     */
    Collection<User> getAll();

    /**
     * Получение количества зарегистрированных пользователей
     * @return количество пользователей
     */
    long getCount();
}
