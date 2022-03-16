package com.epam.data.dao;

import java.util.Map;

import com.epam.entity.User;
import com.epam.entity.UserQuiz;

public interface UserQuizDao {
	
    Map<User, UserQuiz> getAllUserQuizzes();

    void setQuiz(User user, UserQuiz quiz);

    void editQuiz(User user, UserQuiz newQuiz);

    void deleteQuiz(User user);

    Map<Integer, UserQuiz> getAllUserQuizzesWithId();
}
