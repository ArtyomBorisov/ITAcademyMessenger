package by.it.academy.homework_1.dao.hibernate.api.mapper;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.dao.hibernate.api.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements IMapper<Message, MessageEntity> {
    private UserMapper userMapper = new UserMapper();

    @Override
    public Message toDto (MessageEntity messageEntity) {
        if (messageEntity == null) {
            return null;
        }
        return new Message(
                messageEntity.getId(),
                userMapper.toDto(messageEntity.getUserFrom()),
                userMapper.toDto(messageEntity.getUserTo()),
                messageEntity.getTextMessage(),
                messageEntity.getTimeSending(),
                messageEntity.getLastUpdate());
    }

    @Override
    public MessageEntity toEntity (Message message) {
        if (message == null) {
            return null;
        }
        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId(message.getId());
        messageEntity.setTextMessage(message.getTextMessage());
        messageEntity.setUserFrom(
                userMapper.toEntity(message.getUserFrom()));
        messageEntity.setUserTo(
                userMapper.toEntity(message.getUserTo()));
        messageEntity.setTimeSending(message.getTimeSending());
        messageEntity.setLastUpdate(message.getLastUpdate());

        return messageEntity;
    }
}
