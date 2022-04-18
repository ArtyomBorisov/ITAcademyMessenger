package by.it.academy.homework_1.dao.hibernate;

import by.it.academy.homework_1.dao.api.EssenceNotFound;
import by.it.academy.homework_1.dao.hibernate.api.IMapper;
import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.dao.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.dao.hibernate.api.IHibernateStorageUser;
import by.it.academy.homework_1.dao.hibernate.api.entity.UserEntity;
import by.it.academy.homework_1.dao.hibernate.api.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository("hibernateStorageUser")
public class HibernateStorageUser implements IHibernateStorageUser {

    private final IMapper<User, UserEntity> mapper = new UserMapper();

    @Override
    public User get(String login) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = query.from(UserEntity.class);
            query.select(root).where(root.get("login").in(login));
            UserEntity userEntity = manager.createQuery(query).getSingleResult();
            manager.getTransaction().commit();

            return this.mapper.toDto(userEntity);
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Collection<User> getAll() {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        List<User> result = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = query.from(UserEntity.class);
            List<UserEntity> entities = manager.createQuery(query.select(root)).getResultList();
            manager.getTransaction().commit();
            for (UserEntity entity : entities) {
                result.add(this.mapper.toDto(entity));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public User create(User user) {
        if (this.get(user.getLogin()) != null) {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }

        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();

            this.create(user, manager);

            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return this.get(user.getLogin());
    }

    @Override
    public User create(User user, EntityManager manager) {
        if (this.get(user.getLogin()) != null) {
            manager.close();
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }

        if (manager == null) {
            manager = HibernateDBInitializer.getInstance().getManager();
        }

        UserEntity userEntity = this.mapper.toEntity(user);

        manager.persist(userEntity);

        return user;
    }

    @Override
    public long getCount() {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<UserEntity> root = query.from(UserEntity.class);
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
    public User update(User user, String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaUpdate<UserEntity> criteriaUpdate = cb.createCriteriaUpdate(UserEntity.class);
            Root<UserEntity> root = criteriaUpdate.from(UserEntity.class);
            criteriaUpdate.set("PASSWORD", user.getPassword())
                    .set("fio", user.getName())
                    .set("birthday", user.getBirthday())
                    .set("dt_update", user.getLastUpdate());

            criteriaUpdate.where(cb.equal(root.get("login"), login))
                    .where(cb.equal(root.get("lastUpdate"), lastUpdate));

            int update = manager.createQuery(criteriaUpdate).executeUpdate();
            manager.getTransaction().commit();

            if (update == 0) {
                throw new EssenceNotFound("Не удалось обновить пользователя с логином \"" + login + "\"");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }

        return this.get(user.getLogin());
    }

    @Override
    public void delete(String login, LocalDateTime lastUpdate) throws EssenceNotFound {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        try {
            manager.getTransaction().begin();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaDelete<UserEntity> criteriaDelete = cb.createCriteriaDelete(UserEntity.class);
            Root<UserEntity> root = criteriaDelete.from(UserEntity.class);

            criteriaDelete.where(cb.equal(root.get("login"), login))
                    .where(cb.equal(root.get("lastUpdate"), lastUpdate));

            int update = manager.createQuery(criteriaDelete).executeUpdate();
            manager.getTransaction().commit();

            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить пользователя с логином \"" + login + "\"");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } finally {
            manager.close();
        }
    }
}
