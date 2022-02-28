package by.it.academy.homework_1.view.api;

import by.it.academy.homework_1.model.User;

public interface IUserService {
    /**
     * метод регистрирует пользователя
     * @param user пользователь
     */
    void signUp(User user);

    /**
     * метод возвращает количество зарегистрированных пользователей
     * @return количество пользователей
     */
    long getCount();

    /**
     * метод возвращает пользователя по логину
     * @param login логин
     * @return пользователь
     */
    User get(String login);
}
