package com.epam.data.impl.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.data.dao.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Component
public class QuizDaoCollectionImpl implements QuizDao {

    Map<Integer, Quiz> quizMap = new HashMap<Integer, Quiz>();

    @Override
    public Map<Integer, Quiz> getAllQuiz() {
        return quizMap;
    }

    @Override
    public void setQuiz(int index, Quiz quiz) {
        quizMap.put(index, quiz);
    }

    @Override
    public void editQuiz(int index, Quiz newQuiz) {
        quizMap.put(index, newQuiz);
    }

    @Override
    public void deleteQuiz(int index) {
        quizMap.remove(index);
    }

    public void changeQuizStatus(int quizCode, String status) {
        quizMap.get(quizCode).setQuizTag(status);
    }

    @Override
    public Quiz getQuiz(int quizId) {
        return null;
    }

    @Override
    public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {
        return null;
    }
}
