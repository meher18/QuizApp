package com.epam.data.impl.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.data.dao.UserQuizDao;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

public class UserQuizDaoImpl implements UserQuizDao {

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
