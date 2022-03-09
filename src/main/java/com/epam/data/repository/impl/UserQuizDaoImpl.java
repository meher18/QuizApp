package com.epam.data.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.data.repository.UserQuizDao;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@Component
@Primary
public class UserQuizDaoImpl implements UserQuizDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public Map<User, UserQuiz> getAllUserQuizzes() {

        Map<User, UserQuiz> userQuizzes = new HashMap<>();
        entityManager.createQuery("select uq from UserQuiz uq", UserQuiz.class).getResultStream()
                .forEach(userQuiz -> userQuizzes.put(userQuiz.getUser(), userQuiz));
        return userQuizzes;
    }

    public Map<Integer, UserQuiz> getAllUserQuizzesWithId() {

        Map<Integer, UserQuiz> userQuizzes = new HashMap<>();
        entityManager.createQuery("select uq from UserQuiz uq", UserQuiz.class).getResultStream()
                .forEach(userQuiz -> userQuizzes.put(userQuiz.getQuizId(), userQuiz));
        return userQuizzes;
    }

    @Override
    public void setQuiz(User user, UserQuiz newUserQuiz) {

        entityManager.getTransaction().begin();
        entityManager.persist(newUserQuiz);
        entityManager.getTransaction().commit();
    }

    @Override
    public void editQuiz(User user, UserQuiz newUserQuiz) {
        entityManager.getTransaction().begin();
        entityManager.persist(newUserQuiz);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteQuiz(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

}
