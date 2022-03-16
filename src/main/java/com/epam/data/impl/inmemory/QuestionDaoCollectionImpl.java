package com.epam.data.impl.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.data.dao.QuestionDao;
import com.epam.entity.Question;


@Component
public class QuestionDaoCollectionImpl implements QuestionDao {

    
    Map<Integer, Question> questionMap = new HashMap<Integer, Question>();

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
