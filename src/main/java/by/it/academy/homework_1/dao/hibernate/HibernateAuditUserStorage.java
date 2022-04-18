package by.it.academy.homework_1.dao.hibernate;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;
import by.it.academy.homework_1.dao.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.dao.hibernate.api.IHibernateAuditUserStorage;
import by.it.academy.homework_1.dao.hibernate.api.entity.AuditUserEntity;
import by.it.academy.homework_1.dao.hibernate.api.entity.UserEntity;
import by.it.academy.homework_1.dao.hibernate.api.mapper.AuditUserMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("hibernateAuditUserStorage")
public class HibernateAuditUserStorage implements IHibernateAuditUserStorage {

    private final IMapper<AuditUser, AuditUserEntity> mapper = new AuditUserMapper();

    @Override
    public AuditUser create(AuditUser auditUser) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            this.create(auditUser, manager);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return auditUser;
    }

    @Override
    public AuditUser update(AuditUser auditUser, Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        return null;
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {

    }

    @Override
    public AuditUser create(AuditUser auditUser, EntityManager manager) {
        if (manager == null) {
            manager = HibernateDBInitializer.getInstance().getManager();
        }

        AuditUserEntity auditUserEntity = this.mapper.toEntity(auditUser);
        auditUserEntity.setUser(manager.find(UserEntity.class, auditUser.getUser().getLogin()));

        manager.persist(auditUserEntity);

        return auditUser;
    }

    @Override
    public List<AuditUser> read(Pageable pageable) {
        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }

            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        List<AuditUser> result = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<AuditUserEntity> query = cb.createQuery(AuditUserEntity.class);
            Root<AuditUserEntity> root = query.from(AuditUserEntity.class);
            query.select(root);
            TypedQuery<AuditUserEntity> temp = manager.createQuery(query);

            if (offset != null) {
                temp.setFirstResult(offset);
            }
            if (limit != null) {
                temp.setMaxResults(limit);
            }

            List<AuditUserEntity> entities = temp.getResultList();
            for (AuditUserEntity entity : entities) {
                result.add(this.mapper.toDto(entity));
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return result;
    }
}
