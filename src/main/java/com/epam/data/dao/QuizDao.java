package com.epam.data.dao;


import java.util.Map;

import com.epam.entity.Question;
import com.epam.entity.Quiz;

public interface QuizDao {
	
    Map<Integer, Quiz> getAllQuiz();

    void setQuiz(int index, Quiz quiz);

    void editQuiz(int index, Quiz newQuiz);

    void deleteQuiz(int index);

    void changeQuizStatus(int quizId, String status);

    Quiz getQuiz(int quizId);

    Map<Integer, Question> getQuestionsForQuiz(Quiz quiz);
}
