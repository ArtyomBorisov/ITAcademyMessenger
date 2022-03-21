package by.it.academy.homework_1.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    private Long id;

    private String userFrom;
    private String userTo;
    private String textMessage;
    private LocalDateTime timeSending;

    private Message() {
    }

    public Message(String userFrom, String userTo, String textMessage, LocalDateTime timeSending) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.textMessage = textMessage;
        this.timeSending = timeSending;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "from")
    public String getUserFrom() {
        return userFrom;
    }

    private void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    @Column(name = "to")
    public String getUserTo() {
        return userTo;
    }

    private void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    @Column(name = "text")
    public String getTextMessage() {
        return textMessage;
    }

    private void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Column(name = "send_date")
    public LocalDateTime getTimeSending() {
        return timeSending;
    }

    private void setTimeSending(LocalDateTime timeSending) {
        this.timeSending = timeSending;
    }
}
