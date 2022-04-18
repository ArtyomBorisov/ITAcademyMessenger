package by.it.academy.homework_1.services.api;

public interface IAuthService {
    /**
     * метод проводит аутентификацию пользователя по логину/паролю
     * @param login - логин
     * @param password - пароль
     * @return true, если аутентификация пройдена
     *         false, если не пройдена
     */
    boolean authentication(String login, String password);
}
