package com.epam.ui.admin.quiz;

import static com.epam.ui.admin.Utility.takeInputForConfirmation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.global.CorrectInput;
import com.epam.service.admin.quizservice.QuizService;

@Component
public class QuizDeleteUi {


    @Autowired
    QuizService quizDeleter;

    public static final Logger LOGGER = LogManager.getLogger(QuizDeleteUi.class);


    public void deleteQuiz() {
        int code = takeCodeForDeletion();
        try {
            quizDeleter.validateCode(code);
            confirmAndDelete(code);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        QuizRedirectUi.redirect();
    }

    public void confirmAndDelete(int code) {

        int confirmationFlag = takeInputForConfirmation();

        if (confirmationFlag == 1) {
            boolean isQuizDeleted = quizDeleter.delete(code);
            if (isQuizDeleted) {
                LOGGER.info("Quiz Deleted");
            } else {
                LOGGER.error("Unable to delete");
            }
        } else if (confirmationFlag == 2) {
            LOGGER.info("Ok Not done");
        } else {
            LOGGER.warn("Invalid input");
            confirmAndDelete(code);
        }
    }

    public int takeCodeForDeletion() {
        LOGGER.info("Please enter the quiz code: ");
        return CorrectInput.getInteger();
    }
}
