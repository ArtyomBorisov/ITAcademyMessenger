package by.it.academy.homework_1.service.api;

import by.it.academy.homework_1.service.dto.User;

public interface IStorageUserService {

    /**
     * метод возвращает объект User по переданному логину
     * @param login - логин
     * @return User - если такой объект имеется в хранилище
     *         null - если такого объекта нет в хранилище
     */
    User getUser(String login);

    /**
     * метод сохраняет переданный объект User в хранилище
     * @param user - объект для сохранения
     */
    void addUserToStorage(User user);

    /**
     * метод проверяет есть ли в хранилище объект с переданным логином
     * @param login - логин
     * @return true - если такой объект есть в хранилище
     *         false - если такого объекта в хранилище нет
     */
    boolean isLoginExist(String login);

    /**
     * метод проводит аутентификацию пользователя по логину и паролю
     * @param login - логин
     * @param password - пароль
     * @return true - если аутентификация пройдена
     *         false - если аутентификация не пройдена
     */
    boolean authenticationUser(String login, String password);

    /**
     * метод возращает количество зарегестрированных пользователей
     * @return количество пользователей
     */
    int getCountUsers();
}
