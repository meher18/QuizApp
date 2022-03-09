package com.epam.ui.admin.quiz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.global.Constants;
import com.epam.global.CorrectInput;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.exceptions.InValidQuizId;

@Component
public class QuizHostingServiceUi {

    @Autowired
    QuizService quizHostingService;

    public static final Logger LOGGER = LogManager.getLogger(QuizHostingServiceUi.class);

    public int takeInputForHosting() {
        LOGGER.info("Enter the quiz code for hosting: ");
        return CorrectInput.getInteger();
    }

    //  check  if quiz is hosted already or not
    public void hostQuiz() {
        try {
            int code = takeInputForHosting();
            AppContext.getApplicationContext().getBean(QuizService.class).validateCode(code);
            boolean isQuizHosted = quizHostingService.hostQuiz(code);
            if (isQuizHosted) {
                LOGGER.info("Quiz is now hosted ");
            } else {
                LOGGER.error("Unable to host the quiz");
            }
        } catch (InValidQuizId e) {
            LOGGER.info(Constants.INVALID_QUIZ_ID);

        }
        QuizRedirectUi.redirect();

    }
}
