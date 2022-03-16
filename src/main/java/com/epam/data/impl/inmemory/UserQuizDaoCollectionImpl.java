package com.epam.data.impl.inmemory;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.dao.UserQuizDao;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;


@Component
public class UserQuizDaoCollectionImpl implements UserQuizDao {

    Map<User, UserQuiz> userQuizMap = new HashMap<User, UserQuiz>();

    @Override
    public Map<User, UserQuiz> getAllUserQuizzes() {
        return userQuizMap;
    }

    @Override
    public void setQuiz(User user, UserQuiz quiz) {
        userQuizMap.put(user, quiz);
    }

    @Override
    public void editQuiz(User user, UserQuiz newQuiz) {
        // to do
    }

    @Override
    public void deleteQuiz(User user) {
        //
    }

    @Override
    public Map<Integer, UserQuiz> getAllUserQuizzesWithId() {
        return null;
    }
}
