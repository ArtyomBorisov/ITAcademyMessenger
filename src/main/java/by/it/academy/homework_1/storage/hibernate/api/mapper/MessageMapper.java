package by.it.academy.homework_1.storage.hibernate.api.mapper;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.hibernate.HibernateStorageUser;
import by.it.academy.homework_1.storage.hibernate.api.IMapper;
import by.it.academy.homework_1.storage.hibernate.api.entity.MessageEntity;

public class MessageMapper implements IMapper<Message, MessageEntity> {
    private HibernateStorageUser storageUser = HibernateStorageUser.getInstance();
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
                        this.storageUser.get(
                                message.getUserTo())));
        messageEntity.setUserFrom(
                userMapper.toEntity(
                        this.storageUser.get(
                                message.getUserFrom())));
        messageEntity.setTextMessage(message.getTextMessage());
        messageEntity.setTimeSending(message.getTimeSending());
        messageEntity.setId(message.getId());

        return messageEntity;
    }
}
