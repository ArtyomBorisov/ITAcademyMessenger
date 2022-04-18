package by.it.academy.homework_1.model;

import java.time.LocalDateTime;

public class AuditUser {
    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private User user;
    private User author;
    private LocalDateTime lastUpdate;

    public AuditUser() {
    }

    public AuditUser(Long id, LocalDateTime dtCreate, String text,
                     User user, User author, LocalDateTime lastUpdate) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.user = user;
        this.author = author;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", author=" + author +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
