package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.view.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {

    private final IStorageUser storageUser;

    public UserService(IStorageUser storageUser) {
        this.storageUser = storageUser;
    }

    @Override
    public void signUp(User user) {
        this.validationForSignUp(user);
        
        user.setRegistration(LocalDateTime.now());
        this.storageUser.add(user);
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
        if (user.getBirthday() == null){
            errorMessage += "Дата рождения обязательна для заполнения. ";
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
}
