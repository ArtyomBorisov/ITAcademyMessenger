package by.it.academy.homework_1.storage.hibernate;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.storage.hibernate.api.IMapper;
import by.it.academy.homework_1.storage.hibernate.api.entity.MessageEntity;
import by.it.academy.homework_1.storage.hibernate.api.mapper.MessageMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateStorageMessage implements IStorageMessage {

    private IMapper<Message, MessageEntity> messageMapper;

    public HibernateStorageMessage(@Lazy MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public List<Message> get(String loginUser) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(root).where(root.get("userTo").get("login").in(loginUser));
        List<Message> data = new ArrayList<>();

        try {
            List<MessageEntity> entities = manager.createQuery(query).getResultList();
            for (MessageEntity entity : entities) {
                data.add(messageMapper.toDto(entity));
            }
        } catch (NoResultException e) {
        }

        manager.getTransaction().commit();
        manager.close();

        return data;
    }

    @Override
    public void add(Message message) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        MessageEntity messageEntity = messageMapper.toEntity(message);

        manager.persist(messageEntity);

        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public long getCount() {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(cb.count(root));
        Long result = manager.createQuery(query).getSingleResult();

        manager.getTransaction().commit();
        manager.close();

        return result;
    }
}
