package by.it.academy.homework_1.model;

import java.time.LocalDateTime;

public class Message {
    private final String userFrom;
    private final String userTo;
    private final String textMessage;
    private final LocalDateTime timeSending;

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
}
