package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.view.api.IAuthService;
import by.it.academy.homework_1.view.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {

    private final IUserService  userService;

    public AuthService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean authentication(String login, String password) {
        User user = this.userService.get(login);

        return user != null && Objects.equals(user.getPassword(), password);
    }
}
