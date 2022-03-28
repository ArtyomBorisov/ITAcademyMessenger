package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.api.IMapper;
import by.it.academy.homework_1.storage.hibernate.api.entity.UserEntity;

public class UserMapper implements IMapper<User, UserEntity> {
    @Override
    public User toDto (UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User(
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getPassword(),
                userEntity.getBirthday(),
                userEntity.getRegistration());
        user.setId(userEntity.getId());

        return user;
    }

    @Override
    public UserEntity toEntity (User user) {
        if (user == null) {
            return null;
        }
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
