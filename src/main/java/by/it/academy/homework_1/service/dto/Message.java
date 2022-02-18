package by.it.academy.homework_1.service.dto;

public class Message {
    private final String userFrom;
    private final String userTo;
    private final String message;

    public Message(String userFrom, String userTo, String message) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getMessage() {
        return message;
    }
}
