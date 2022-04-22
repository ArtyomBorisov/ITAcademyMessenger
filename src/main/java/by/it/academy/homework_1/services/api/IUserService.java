package by.it.academy.homework_1.services.api;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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

    Collection<User> getAll();

    User update(User user, String login, LocalDateTime lastUpdate);
}
