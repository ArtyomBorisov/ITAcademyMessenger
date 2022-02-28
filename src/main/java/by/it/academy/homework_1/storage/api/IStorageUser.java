package by.it.academy.homework_1.storage.api;

import by.it.academy.homework_1.model.User;

public interface IStorageUser {

    /**
     * метод возвращает объект User по переданному логину
     * @param login - логин
     * @return User - если такой объект имеется в хранилище
     *         null - если такого объекта нет в хранилище
     */
    User get(String login);

    /**
     * метод сохраняет переданный объект User в хранилище
     * @param user - объект для сохранения
     */
    void add(User user);

    /**
     * метод возращает количество зарегестрированных пользователей
     * @return количество пользователей
     */
    int getCount();
}
