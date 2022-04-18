package by.it.academy.homework_1.dao.hibernate;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.model.Message;
import by.it.academy.homework_1.dao.api.IStorageMessage;
import by.it.academy.homework_1.dao.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.dao.hibernate.api.entity.MessageEntity;
import by.it.academy.homework_1.dao.hibernate.api.mapper.MessageMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("hibernateStorageMessage")
public class HibernateStorageMessage implements IStorageMessage {

    private IMapper<Message, MessageEntity> messageMapper = new MessageMapper();

    @Override
    public List<Message> get(String loginUser) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        List<Message> data = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
            Root<MessageEntity> root = query.from(MessageEntity.class);
            query.select(root).where(root.get("userTo").get("login").in(loginUser));

            List<MessageEntity> entities = manager.createQuery(query).getResultList();
            for (MessageEntity entity : entities) {
                data.add(this.messageMapper.toDto(entity));
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return data;
    }

    @Override
    public Message create(Message message) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();

            MessageEntity messageEntity = messageMapper.toEntity(message);

            manager.persist(messageEntity);

            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return message;
    }

    @Override
    public long getCount() {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<MessageEntity> root = query.from(MessageEntity.class);
            query.select(cb.count(root));
            Long result = manager.createQuery(query).getSingleResult();
            manager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Message update(Message message, Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        return null;
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {

    }
}
