package by.it.academy.homework_1.storage.hibernate.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "app", name = "message")
public class MessageEntity {
    private Long id;
    private UserEntity userFrom;
    private UserEntity userTo;
    private String textMessage;
    private LocalDateTime timeSending;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "\"from\"", nullable = false)
    public UserEntity getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserEntity userFrom) {
        this.userFrom = userFrom;
    }

    @ManyToOne
    @JoinColumn(name = "\"to\"", nullable = false)
    public UserEntity getUserTo() {
        return userTo;
    }

    public void setUserTo(UserEntity userTo) {
        this.userTo = userTo;
    }

    @Column(name = "text", nullable = false)
    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Column(name = "send_date", nullable = false)
    public LocalDateTime getTimeSending() {
        return timeSending;
    }

    public void setTimeSending(LocalDateTime timeSending) {
        this.timeSending = timeSending;
    }
}
