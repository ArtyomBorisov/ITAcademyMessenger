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
import javax.persistence.criteria.*;
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
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaUpdate<AuditUserEntity> criteriaUpdate = cb.createCriteriaUpdate(AuditUserEntity.class);
            Root<AuditUserEntity> root = criteriaUpdate.from(AuditUserEntity.class);
            criteriaUpdate.set("text", auditUser.getText())
                    .set("user", auditUser.getUser())
                    .set("author", auditUser.getAuthor())
                    .set("dt_update", auditUser.getLastUpdate());

            criteriaUpdate.where(cb.equal(root.get("login"), id))
                    .where(cb.equal(root.get("lastUpdate"), lastUpdate));

            int update = manager.createQuery(criteriaUpdate).executeUpdate();
            manager.getTransaction().commit();

            if (update == 0) {
                throw new EssenceNotFound("Не удалось обновить аудит с id \"" + id + "\"");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return auditUser;
    }

    @Override
    public void delete(Long id, LocalDateTime lastUpdate) throws EssenceNotFound {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaDelete<AuditUserEntity> criteriaDelete = cb.createCriteriaDelete(AuditUserEntity.class);
            Root<AuditUserEntity> root = criteriaDelete.from(AuditUserEntity.class);

            criteriaDelete.where(cb.equal(root.get("id"), id))
                    .where(cb.equal(root.get("lastUpdate"), lastUpdate));

            int update = manager.createQuery(criteriaDelete).executeUpdate();
            manager.getTransaction().commit();

            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить аудит с id \"" + id + "\"");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }
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
    public List<AuditUser> read() {
        Pageable pageable = null;
        return this.read(pageable);
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

    @Override
    public List<AuditUser> read(String login) {
        return this.read(login, null);
    }

    @Override
    public List<AuditUser> read(String login, Pageable pageable) {
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

            Predicate predicate = cb.or(
                    cb.equal(root.get("user"), login),
                    cb.equal(root.get("author"), login));

            query.where(predicate);

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
