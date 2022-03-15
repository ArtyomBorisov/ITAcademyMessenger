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
    private String login;
    private String password;
    private String name;
    private LocalDate birthday;
    private LocalDateTime registration;

    public User() {
    }

    public User(String login, String password, String name, LocalDate birthday, LocalDateTime registration) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.registration = registration;
    }

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

    public static class Builder{
        private String login;
        private String password;
        private String name;
        private LocalDate birthday;
        private LocalDateTime registration;

        private Builder() {
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setRegistration(LocalDateTime registration) {
            this.registration = registration;
            return this;
        }

        public static Builder createBuilder(){
            return new Builder();
        }

        public User build(){
            return new User(login, password, name, birthday, registration);
        }
    }
}
