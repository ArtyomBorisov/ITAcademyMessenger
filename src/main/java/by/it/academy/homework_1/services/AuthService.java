package by.it.academy.homework_1.services;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.services.api.IAuthService;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
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
