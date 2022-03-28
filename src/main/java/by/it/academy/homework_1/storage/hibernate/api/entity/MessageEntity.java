package by.it.academy.homework_1.storage.hibernate.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "app", name = "messages")
public class MessageEntity {
    private Long id;

    private UserEntity userFrom;
    private UserEntity userTo;
    private String textMessage;
    private LocalDateTime timeSending;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "\"from\"", nullable = false)
    @ManyToOne
    public UserEntity getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserEntity userFrom) {
        this.userFrom = userFrom;
    }

    @JoinColumn(name = "\"to\"", nullable = false)
    @ManyToOne
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
