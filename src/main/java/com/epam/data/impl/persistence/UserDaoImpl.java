package com.epam.data.impl.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import com.epam.data.dao.UserDao;
import com.epam.entity.User;

public class UserDaoImpl implements UserDao {

    
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void editUser(int userId, User newUser) {
        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(int id) {

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
