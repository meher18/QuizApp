package com.epam.data.library;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.data.repository.UserQuizDao;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserQuizLibrary {

    @Autowired
    UserQuizDao userQuizDao;

    public Map<User, UserQuiz> getUserQuizMap() {
        return userQuizDao.getAllUserQuizzes();
    }

    public Map<Integer, UserQuiz> getUserQuizzesWithId() {
        return userQuizDao.getAllUserQuizzesWithId();
    }

    public boolean addQuiz(User user, UserQuiz quiz) {
        userQuizDao.setQuiz(user, quiz);
        return true;
    }

    public boolean editQuiz(User user, UserQuiz quiz) {
        userQuizDao.editQuiz(user, quiz);
        return true;
    }

    public boolean deleteQuiz(User user) {
        userQuizDao.deleteQuiz(user);
        return true;
    }
}
