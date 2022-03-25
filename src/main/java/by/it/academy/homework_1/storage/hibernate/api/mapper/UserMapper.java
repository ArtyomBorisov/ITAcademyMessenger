package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.api.entity.UserEntity;

public class UserMapper {
    public User toDto (UserEntity userEntity) {
        User user = new User(
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getPassword(),
                userEntity.getBirthday(),
                userEntity.getRegistration());
        user.setId(userEntity.getId());

        return user;
    }

    public UserEntity toEntity (User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setRegistration(user.getRegistration());

        return userEntity;
    }
}
