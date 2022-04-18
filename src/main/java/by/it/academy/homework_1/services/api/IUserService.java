package by.it.academy.homework_1.services.api;

import by.it.academy.homework_1.model.User;

import java.time.LocalDateTime;

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

    void delete(String login, LocalDateTime lastUpdate);
}
