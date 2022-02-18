package by.it.academy.homework_1.service.dto;

import java.time.LocalDate;

/**
 * login - логин
 * password - пароль
 * name - ФИО
 * birthday - дата рождения
 */
public class User {
    private final String login;
    private String password;
    private String name;
    private LocalDate birthday;

    public User(String login, String password, String name, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
