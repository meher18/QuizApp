package com.epam.data.repository.inmemory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Component
public class QuizDaoCollectionImpl implements QuizDao {

    @Autowired
    Map<Integer, Quiz> quizMap ;

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
