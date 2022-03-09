package com.epam.ui.admin.question;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.global.CorrectInput;
import com.epam.ui.admin.AdminDashBoardUi;



public class QuestionRedirectUi {
    public static final Logger LOGGER = LogManager.getLogger(QuestionRedirectUi.class);
    private QuestionRedirectUi()
    {

    }
    public static void redirect() {
        LOGGER.info("press 1 > to go to question module");
        LOGGER.info("press 2 > to go to admin module");

        int choice = CorrectInput.getInteger();
        if (choice == 1) {
            QuestionModuleUi.performQuestionOperation();
        } else {
            AdminDashBoardUi.showAdminTasks();
        }
    }
}
