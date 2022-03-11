package com.epam.service.admin.questionservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.data.library.QuestionsLibrary;
import com.epam.entity.Question;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.exceptions.InValidQuestionId;
import com.epam.util.Constants;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionService {

    @Autowired
    QuestionsLibrary questionsLibrary;

    Map<Integer, Question> questions;

    public  Map<Integer, Question> getQuestions() {

        if(questions == null)
        {
            questions = questionsLibrary.getQuestions();
        }
        return questions;
    }

    public Question getQuestion(int questionId) {
    	
        return questionsLibrary.getQuestion(questionId);
    }

    public void validateQuestionId(int questionId) throws InValidQuestionId, InValidQuestionDeletion{

        if (!getQuestions().containsKey(questionId)) {
        	
            throw new InValidQuestionId(Constants.INVALID_QUESTION_ID);
        }
        if(questionsLibrary.getNoQuizzesForQuestionId(questionId) > 0)
        {
            throw new InValidQuestionDeletion(Constants.INVALID_QUESTION_DELETION);
        }
    }

    public boolean createQuestion(Question newQuestion) {
// question Creator
        boolean isQuestionCreated = false;
        
        if (newQuestion != null) {
            // this id is used when we are implementing collections as db
            int questionId = 0;
            boolean isAdded = questionsLibrary.addQuestion(questionId, newQuestion);
            if (isAdded) {
                isQuestionCreated = true;
            }
        }
        return isQuestionCreated;
    }

    public boolean update(Question newQuestion, int id) {

        boolean isQuestionUpdated = false;
        
        if (newQuestion != null) {
            isQuestionUpdated = questionsLibrary.editQuestion(id, newQuestion);
        }
        return isQuestionUpdated;
    }

    public boolean delete(int questionId) {
    	
        return questionId > 0 && questionsLibrary.deleteQuestion(questionId);
    }
}
