package com.epam.ui.viewer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.UserQuiz;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.user.UserQuizService;
import com.epam.ui.user.UserSession;

@Component
public class UserQuizViewer {

    public static final Logger LOGGER = LogManager.getLogger(UserQuizViewer.class);

    public void viewAllUserQuizzes() {

        UserQuizService userQuizService = AppContext.getApplicationContext().getBean(UserQuizService.class);
        userQuizService.setUser(UserSession.getLoggedUser());

        Map<Integer, UserQuiz> quizzes = userQuizService
                .getAllUserQuizzes()
                .entrySet()
                .stream()
                .filter(userUserQuizEntry -> userUserQuizEntry.getValue().getUser().getId() == UserSession.getLoggedUser().getId())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        if (quizzes.size() > 0) {
        	
            quizzes.forEach((userQuizId, userQuiz) -> {
                String quizHeading = userQuiz.getQuiz().getQuizName() +
                        " - marks -> " + userQuiz.getResult();
                LOGGER.info(quizHeading);
            });
            
        } else {
            LOGGER.debug(UserSession.getLoggedUser().getId());
            LOGGER.info("No Participation... from your side");
        }

    }

    // view all hosted quizzes by admin
    
    public boolean viewAllQuizzes() {

        QuizService quizService = AppContext.getApplicationContext().getBean(QuizService.class);
        AtomicBoolean areQuizzesAvailable = new AtomicBoolean(false);

        if (quizService.getAllQuizzes().size() > 0) {
            quizService.getAllQuizzes().forEach((key, quiz) ->{
                if (quiz.getQuizTag().equalsIgnoreCase("HOSTED")) {
                    LOGGER.info("Quiz Code [ {} ] ,  Quiz Title {}",key ,  quiz.getQuizName());
                    areQuizzesAvailable.set(true);
                }
            });
        }
        if (!areQuizzesAvailable.get()) {
            LOGGER.info("No Hosted Quizzes... ");
        } else {
            LOGGER.info("Select the quiz index from above quizzes...");
        }
        return areQuizzesAvailable.get();
    }

}
