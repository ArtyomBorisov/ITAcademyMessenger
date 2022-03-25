package by.it.academy.homework_1.storage.hibernate;

import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.storage.api.IStorageMessage;
import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.storage.hibernate.api.entity.MessageEntity;
import by.it.academy.homework_1.storage.hibernate.api.mapper.MessageMapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateStorageMessage implements IStorageMessage {
    private static final HibernateStorageMessage instance = new HibernateStorageMessage();

    private HibernateStorageMessage() {
    }

    @Override
    public List<Message> get(String loginUser) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(root).where(root.get("userTo").in(loginUser));
        List<Message> data = new ArrayList<>();

        try {
            List<MessageEntity> entities = manager.createQuery(query).getResultList();
            MessageMapper mapper = new MessageMapper();
            for (MessageEntity entity : entities) {
                data.add(mapper.toDto(entity));
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

        MessageMapper mapper = new MessageMapper();
        MessageEntity messageEntity = mapper.toEntity(message);

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

    public static HibernateStorageMessage getInstance() {
        return instance;
    }
}
