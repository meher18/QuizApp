package com.epam.ui.admin.question;

import static com.epam.ui.admin.Utility.takeInputForConfirmation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.global.CorrectInput;
import com.epam.service.admin.questionservice.QuestionService;

@Component
public class QuestionDeleteUi {

    public static final Logger LOGGER = LogManager.getLogger(QuestionDeleteUi.class);

    public void deleteQuestion() {
        QuestionService questionService = AppContext.getApplicationContext().getBean(QuestionService.class);
        int questionId = takeIdForDeletingQuestion();
        // check for validity
        try {
            questionService.validateQuestionId(questionId);
            confirmAndDelete(questionService, questionId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        QuestionRedirectUi.redirect();
    }

    public int takeIdForDeletingQuestion() {
        LOGGER.info("Please enter the id of the question to delete");
        return CorrectInput.getInteger();
    }

    // take confirmation and delete
    public void confirmAndDelete(QuestionService questionService, int questionId) {

        int confirmationFlag = takeInputForConfirmation();
        if (confirmationFlag == 1) {
            // delete the question here
            boolean isQuestionDeleted = questionService.delete(questionId);
            if (isQuestionDeleted) {
                LOGGER.info("Question deleted");
            } else {
                LOGGER.error("Question not deleted");
            }
        } else {
            LOGGER.info("Ok Not done");
        }
    }
}
