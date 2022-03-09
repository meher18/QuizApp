package com.epam.data.repository.inmemory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionDao;
import com.epam.entity.Question;


@Component
public class QuestionDaoCollectionImpl implements QuestionDao {

    @Autowired
    Map<Integer, Question> questionMap;

    @Override
    public Question getQuestion(int questionId) {
        return null;
    }

    @Override
    public Map<Integer, Question> getAllQuestions() {
        return questionMap;
    }

    @Override
    public void setQuestion(int index, Question question) {
        questionMap.put(index, question);
    }

    @Override
    public void editQuestion(int index, Question newQuestion) {
        questionMap.put(index, newQuestion);
    }

    @Override
    public void deleteQuestion(int index) {
        questionMap.remove(index);
    }

    @Override
    public int getNoOfQuizzesForQuestionId(int questionId) {
        return 0;
    }
}
