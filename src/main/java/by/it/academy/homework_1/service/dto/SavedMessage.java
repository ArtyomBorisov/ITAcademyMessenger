package by.it.academy.homework_1.service.dto;

import java.time.LocalDateTime;

public class SavedMessage {

    private final LocalDateTime timeSending;
    private final Message message;

    public SavedMessage(LocalDateTime timeSending, Message message) {
        this.timeSending = timeSending;
        this.message = message;
    }

    public LocalDateTime getTimeSending() {
        return timeSending;
    }

    public Message getMessage() {
        return message;
    }
}
