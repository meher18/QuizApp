package com.epam.ui.user.quiz;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.QuestionAnswer;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;
import com.epam.global.CorrectInput;
import com.epam.service.user.UserQuizService;
import com.epam.ui.user.UserRedirectUi;
import com.epam.ui.viewer.UserQuizViewer;

@Component
public class UserQuizUi {

    @Autowired
    UserQuizViewer userQuizViewer;

    User user;

    public static final Logger LOGGER = LogManager.getLogger(UserQuizUi.class);

    public Integer takeInputForUserQuiz() {
        return CorrectInput.getInteger();
    }

    public Integer takeInputForConfirmation() {
        LOGGER.info("Are you sure want to start the quiz");
        LOGGER.info("Press 1 > YES");
        LOGGER.info("Press 2 > NO");
        return CorrectInput.getInteger();
    }

    public void takeQuiz(User user) {

        this.user = user;
        UserQuizService userQuizService = AppContext.getApplicationContext()
                .getBean(UserQuizService.class);
        userQuizService.setUser(user);
        userQuizService.setUserQuiz(AppContext.getApplicationContext().getBean(UserQuiz.class));

        boolean areQuizzesAvailable = userQuizViewer.viewAllQuizzes();

        if (!areQuizzesAvailable) {
            LOGGER.info("No Quizzes Available");
            UserRedirectUi.redirect();
        } else {

            // todo validate the quiz id

            int quizId = takeInputForUserQuiz();

            boolean isQuizTaken = userQuizService.checkIfQuizTaken(quizId);
            if (isQuizTaken) {
                LOGGER.info("You have already taken this quiz");
            } else {
                takeConfirmationAndStartQuiz(quizId, userQuizService);
            }


            UserRedirectUi.redirect();
        }
    }

    public void takeConfirmationAndStartQuiz(int indexOfQuiz, UserQuizService userQuizService) {

        int confirmationFlag = takeInputForConfirmation();
        if (confirmationFlag == 1) {

            userQuizService.getUserQuiz().setQuizById(indexOfQuiz);

            StartQuizUi startQuizUi = AppContext.getApplicationContext().getBean(StartQuizUi.class);

            List<QuestionAnswer> userAnswers = startQuizUi
                    .startQuiz(userQuizService.getUserQuiz().getQuiz().getQuestions());
            userQuizService.setAnswers(userAnswers);
            userQuizService.saveQuiz();

        } else if (confirmationFlag == 2) {
            UserRedirectUi.redirect();
        }
    }
}
