package by.it.academy.homework_1.model;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String userFrom;
    private String userTo;
    private String textMessage;
    private LocalDateTime timeSending;

    public Message(String userFrom, String userTo, String textMessage, LocalDateTime timeSending) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.textMessage = textMessage;
        this.timeSending = timeSending;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public LocalDateTime getTimeSending() {
        return timeSending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", textMessage='" + textMessage + '\'' +
                ", timeSending=" + timeSending +
                '}';
    }
}
