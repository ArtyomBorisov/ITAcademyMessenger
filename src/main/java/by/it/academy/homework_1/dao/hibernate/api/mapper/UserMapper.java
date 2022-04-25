package by.it.academy.homework_1.dao.hibernate.api.mapper;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.dao.hibernate.api.entity.UserEntity;

public class UserMapper implements IMapper<User, UserEntity> {
    @Override
    public User toDto (UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.Builder.createBuilder()
                .setLogin(userEntity.getLogin())
                .setPassword(userEntity.getPassword())
                .setName(userEntity.getName())
                .setBirthday(userEntity.getBirthday())
                .setLastUpdate(userEntity.getLastUpdate())
                .setRegistration(userEntity.getRegistration())
                .setAccessible(userEntity.isAccessible())
                .build();
    }

    @Override
    public UserEntity toEntity (User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setLastUpdate(user.getLastUpdate());
        userEntity.setRegistration(user.getRegistration());
        userEntity.setAccessible(user.isAccessible());

        return userEntity;
    }
}
