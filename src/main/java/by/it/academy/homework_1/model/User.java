package by.it.academy.homework_1.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    private Long id;

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

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "fio")
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    private void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "date_reg")
    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
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
