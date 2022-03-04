package by.it.academy.homework_1.view;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.DBStorageUser;
import by.it.academy.homework_1.storage.api.IStorageUser;
import by.it.academy.homework_1.view.api.IMessageService;
import by.it.academy.homework_1.view.api.IUserService;

import java.time.LocalDateTime;

public class UserService implements IUserService {

    private static final UserService instance = new UserService();
    private final IStorageUser storageUser;
    private final IMessageService messageService;

    private UserService() {
        this.storageUser = DBStorageUser.getInstance();
        this.messageService = MessageService.getInstance();
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

    public static UserService getInstance() {
        return instance;
    }
}
