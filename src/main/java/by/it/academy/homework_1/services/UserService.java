package by.it.academy.homework_1.services;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.api.IStorageUser;
import by.it.academy.homework_1.services.api.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IStorageUser storageUser;

    public UserService(IStorageUser storageUser) {
        this.storageUser = storageUser;
    }

    @Override
    @Transactional
    public void signUp(User user) {
        this.validationForSignUp(user);

        LocalDateTime now = LocalDateTime.now();
        user.setRegistration(now);
        user.setLastUpdate(now);
        this.storageUser.create(user);
    }

    private void validationForSignUp(User user){
        String errorMessage = "";
        if (this.nullOrEmpty(user.getLogin())){
            errorMessage += "Логин обязателен для заполнения. ";
        }
        if (this.nullOrEmpty(user.getPassword())){
            errorMessage += "Пароль обязателен для заполнения. ";
        }
        if (this.nullOrEmpty(user.getName())){
            errorMessage += "ФИО обязательно для заполнения. ";
        }
        if (!errorMessage.isEmpty()){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean nullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    @Override
    public long getCount() {
        return this.storageUser.getCount();
    }

    @Override
    public User get(String login) {
        return this.storageUser.get(login);
    }

    @Override
    public Collection<User> getAll() {
        return this.storageUser.getAll();
    }

    @Override
    public User update(User user, String login, LocalDateTime lastUpdate) {
        user.setLastUpdate(LocalDateTime.now());
        return this.storageUser.update(user, login, lastUpdate);
    }
}
