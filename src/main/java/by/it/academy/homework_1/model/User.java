package by.it.academy.homework_1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime registration;

    public User(String login, String password, String name, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
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

    public LocalDateTime getRegistration() {
        return registration;
    }
}
