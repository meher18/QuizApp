package com.epam.ui.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.config.AppContext;
import com.epam.global.CorrectInput;
import com.epam.service.admin.AdminDash;
import com.epam.ui.admin.question.QuestionModuleUi;
import com.epam.ui.admin.quiz.QuizModuleUi;

@Component
public class AdminDashBoardUi {
    public static final Logger LOGGER = LogManager.getLogger(AdminDashBoardUi.class);

    private AdminDashBoardUi() {

    }
    public static int takeInputForAdminModule() {
        String heading = "-".repeat(10) + " Admin Dashboard " + "-".repeat(10);
        LOGGER.info(heading);
        LOGGER.info("- Choose Below Operations");
        LOGGER.info("Press 1 > QUESTION MODULE");
        LOGGER.info("Press 2 > QUIZ MODULE");
        LOGGER.info("Press 3 > TO LOGOUT");
        return CorrectInput.getInteger();
    }

    public static void showAdminTasks() {

        int choice = takeInputForAdminModule();
        switch (choice) {
            case 1: {
                QuestionModuleUi.performQuestionOperation();
            }
            break;
            case 2: {
                QuizModuleUi quizModuleUi = AppContext.getApplicationContext().getBean(QuizModuleUi.class);
                quizModuleUi.showQuizModuleTasks();
            }
            break;
            case 3: {
                if (AdminDash.logoutAdmin()) {
                    LOGGER.info("you logged out ...");
                    App.startApplication();
                } else {
                    LOGGER.info("Unable to log out ...");
                }
            }
            break;
            default: {
                showAdminTasks();
            }
        }
    }

}
