package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.api.IMapper;
import by.it.academy.homework_1.storage.hibernate.api.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements IMapper<Message, MessageEntity> {
    private UserMapper userMapper = new UserMapper();

    @Override
    public Message toDto (MessageEntity messageEntity) {
        if (messageEntity == null) {
            return null;
        }
        Message message = new Message(
                messageEntity.getUserFrom() == null ? null : messageEntity.getUserFrom().getLogin(),
                messageEntity.getUserTo() == null ? null : messageEntity.getUserTo().getLogin(),
                messageEntity.getTextMessage(),
                messageEntity.getTimeSending());
        message.setId(messageEntity.getId());

        return message;
    }

    @Override
    public MessageEntity toEntity (Message message) {
        if (message == null) {
            return null;
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUserTo(
                userMapper.toEntity(
                        User.Builder.createBuilder().setLogin(message.getUserTo()).build()));
        messageEntity.setUserFrom(
                userMapper.toEntity(
                        User.Builder.createBuilder().setLogin(message.getUserFrom()).build()));
        messageEntity.setTextMessage(message.getTextMessage());
        messageEntity.setTimeSending(message.getTimeSending());
        messageEntity.setId(message.getId());

        return messageEntity;
    }
}
