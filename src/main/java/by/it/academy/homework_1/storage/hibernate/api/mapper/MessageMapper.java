package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.hibernate.api.entity.MessageEntity;

public class MessageMapper {
    public Message toDto (MessageEntity messageEntity) {
        Message message = new Message(
                messageEntity.getUserFrom(),
                messageEntity.getUserTo(),
                messageEntity.getTextMessage(),
                messageEntity.getTimeSending());
        message.setId(messageEntity.getId());

        return message;
    }

    public MessageEntity toEntity (Message message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUserTo(message.getUserTo());
        messageEntity.setUserFrom(message.getUserFrom());
        messageEntity.setTextMessage(message.getTextMessage());
        messageEntity.setTimeSending(message.getTimeSending());
        messageEntity.setId(message.getId());

        return messageEntity;
    }
}
