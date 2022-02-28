package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.view.api.IAuthService;
import by.it.academy.homework_1.view.api.IUserService;

import java.util.Objects;

public class AuthService implements IAuthService {

    private final static AuthService instance = new AuthService();
    private final IUserService  userService;

    private AuthService() {
        this.userService = UserService.getInstance();
    }

    @Override
    public boolean authentication(String login, String password) {
        User user = this.userService.get(login);

        return user != null && Objects.equals(user.getPassword(), password);
    }

    public static AuthService getInstance() {
        return instance;
    }
}
