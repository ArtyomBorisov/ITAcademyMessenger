package by.it.academy.homework_1.storage.hibernate;

import by.it.academy.homework_1.model.User;
import by.it.academy.homework_1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.homework_1.storage.hibernate.api.IHibernateStorageUser;
import by.it.academy.homework_1.storage.hibernate.api.entity.UserEntity;
import by.it.academy.homework_1.storage.hibernate.api.mapper.UserMapper;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HibernateStorageUser implements IHibernateStorageUser {
    private static final HibernateStorageUser instance = new HibernateStorageUser();

    private HibernateStorageUser() {
    }

    @Override
    public User get(String login) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(root.get("login").in(login));

        try {
            UserEntity userEntity = manager.createQuery(query).getSingleResult();
            manager.getTransaction().commit();
            UserMapper mapper = new UserMapper();
            return mapper.toDto(userEntity);
        } catch (NoResultException e) {
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void add(User user) {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        this.add(user, manager);

        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void add(User user, EntityManager manager) {
        if (this.get(user.getLogin()) != null) {
            manager.close();
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }

        if (manager == null) {
            manager = HibernateDBInitializer.getInstance().getManager();
        }

        UserMapper mapper = new UserMapper();
        UserEntity userEntity = mapper.toEntity(user);

        manager.persist(userEntity);
    }

    @Override
    public long getCount() {
        EntityManager manager = HibernateDBInitializer.getInstance().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(cb.count(root));
        Long result = manager.createQuery(query).getSingleResult();

        manager.getTransaction().commit();
        manager.close();

        return result;
    }

    public static HibernateStorageUser getInstance() {
        return instance;
    }
}