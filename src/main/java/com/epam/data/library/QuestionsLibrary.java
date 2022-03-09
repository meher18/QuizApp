package com.epam.data.library;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionDao;
import com.epam.entity.Question;

@Component
public class QuestionsLibrary {

    @Autowired
    @Qualifier("questionDaoImpl")
    QuestionDao questionDao;

    public Question getQuestion(int questionId) {

        return questionDao.getQuestion(questionId);
    }

    public Map<Integer, Question> getQuestions() {
        return questionDao.getAllQuestions();
    }

    public boolean addQuestion(int index, Question question) {
        boolean isQuestionValid = false;
        if (question != null) {
            questionDao.setQuestion(index, question);
            isQuestionValid = true;
        }
        return isQuestionValid;
    }

    public boolean editQuestion(int index, Question newQuestion) {
        questionDao.editQuestion(index, newQuestion);
        return true;
    }

    public boolean deleteQuestion(int index) {
        questionDao.deleteQuestion(index);
        return true;
    }

    public int getNoQuizzesForQuestionId(int questionId) {
        return questionDao.getNoOfQuizzesForQuestionId(questionId);
    }
}
