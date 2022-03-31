package by.it.academy.homework_1.storage.hibernate.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "app", name = "audit_user")
public class AuditUserEntity {
    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private UserEntity user;
    private UserEntity author;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "date_create", nullable = false)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}
