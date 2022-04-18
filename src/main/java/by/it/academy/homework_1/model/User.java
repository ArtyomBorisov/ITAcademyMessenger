package by.it.academy.homework_1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private String login;
    private String password;
    private String name;
    private LocalDate birthday;
    private LocalDateTime registration;
    private LocalDateTime lastUpdate;

    public User() {
    }

    public User(String login, String password, String name, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
    }

    public User(String login, String password, String name,
                LocalDate birthday, LocalDateTime registration, LocalDateTime lastUpdate) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.registration = registration;
        this.lastUpdate = lastUpdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static class Builder{
        private String login;
        private String password;
        private String name;
        private LocalDate birthday;
        private LocalDateTime registration;
        private LocalDateTime lastUpdate;

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

        public Builder setLastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public static Builder createBuilder(){
            return new Builder();
        }

        public User build(){
            return new User(login, password, name, birthday, registration, lastUpdate);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", registration=" + registration +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
