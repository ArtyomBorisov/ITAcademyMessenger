package by.it.academy.homework_1.model;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private User userFrom;
    private User userTo;
    private String textMessage;
    private LocalDateTime timeSending;
    private LocalDateTime lastUpdate;

    public Message() {
    }

    public Message(User userFrom, User userTo, String textMessage, LocalDateTime timeSending) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.textMessage = textMessage;
        this.timeSending = timeSending;
    }

    public Message(Long id, User userFrom, User userTo, String textMessage,
                   LocalDateTime timeSending, LocalDateTime lastUpdate) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.textMessage = textMessage;
        this.timeSending = timeSending;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public LocalDateTime getTimeSending() {
        return timeSending;
    }

    public void setTimeSending(LocalDateTime timeSending) {
        this.timeSending = timeSending;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", textMessage='" + textMessage + '\'' +
                ", timeSending=" + timeSending +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
