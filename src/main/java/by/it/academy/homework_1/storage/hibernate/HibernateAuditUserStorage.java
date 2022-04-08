package by.it.academy.homework_1.storage.hibernate;

import by.it.academy.homework_1.model.AuditUser;
import by.it.academy.homework_1.model.Pageable;
import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.storage.hibernate.api.IHibernateAuditUserStorage;
import by.it.academy.homework_1.storage.hibernate.api.entity.AuditUserEntity;
import by.it.academy.homework_1.storage.hibernate.api.entity.UserEntity;
import by.it.academy.homework_1.storage.hibernate.api.mapper.AuditUserMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateAuditUserStorage implements IHibernateAuditUserStorage {

    @Override
    public Long create(AuditUser auditUser) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        Long result = this.create(auditUser, manager);

        manager.getTransaction().commit();
        manager.close();

        return result;
    }

    @Override
    public Long create(AuditUser auditUser, EntityManager manager) {
        if (manager == null) {
            manager = HibernateDBInitializer.getInstance().getManager();
        }

        AuditUserMapper mapper = new AuditUserMapper();
        AuditUserEntity auditUserEntity = mapper.toEntity(auditUser);
        auditUserEntity.setUser(manager.find(UserEntity.class, auditUser.getUser().getLogin()));

        manager.persist(auditUserEntity);

        return auditUserEntity.getId();
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

        manager.getTransaction().commit();

        List<AuditUser> result = new ArrayList<>();
        AuditUserMapper mapper = new AuditUserMapper();

        for (AuditUserEntity entity : entities) {
            result.add(mapper.toDto(entity));
        }

        return result;
    }
}
