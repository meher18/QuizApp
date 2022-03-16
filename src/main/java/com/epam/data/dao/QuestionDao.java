package com.epam.data.dao;

import java.util.Map;

import com.epam.entity.Question;

public interface QuestionDao{

    Question getQuestion(int questionId);

    Map<Integer, Question> getAllQuestions();

    void setQuestion(int index, Question question);

    void editQuestion(int index, Question newQuestion);

    void deleteQuestion(int index);

    int getNoOfQuizzesForQuestionId(int questionId);
}
