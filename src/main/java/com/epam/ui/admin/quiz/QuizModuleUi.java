package com.epam.ui.admin.quiz;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.global.CorrectInput;
import com.epam.ui.admin.AdminDashBoardUi;
import com.epam.ui.viewer.QuizViewer;

@Component
public class QuizModuleUi {

    public static final Logger LOGGER = LogManager.getLogger(QuizModuleUi.class);

    static Map<Integer, QuizOperationUi> questionServiceUiFunctions = new HashMap<>();

    static {
        questionServiceUiFunctions.put(1, () -> AppContext.getApplicationContext().getBean(QuizBuilderUi.class).buildQuiz());
        questionServiceUiFunctions.put(2, () -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).updateQuiz());
        questionServiceUiFunctions.put(3, () -> AppContext.getApplicationContext().getBean(QuizDeleteUi.class).deleteQuiz());
        questionServiceUiFunctions.put(4, () -> AppContext.getApplicationContext().getBean(QuizHostingServiceUi.class).hostQuiz());
        questionServiceUiFunctions.put(5, () -> {
            AppContext.getApplicationContext().getBean(QuizViewer.class).viewAllQuizzes();
            QuizRedirectUi.redirect();
        });
        questionServiceUiFunctions.put(6, () -> AppContext.getApplicationContext().getBean(AdminDashBoardUi.class).showAdminTasks());
    }

    public void showQuizModuleTasks() {
        int choice = takeInputForModuleTask();
        questionServiceUiFunctions.get(choice).operation();
    }

    public int takeInputForModuleTask() {
        int choice = 0;
        quizOperations();
        choice = CorrectInput.getInteger();
        while (!QuizModuleTask.operations.containsKey(choice)) {
            LOGGER.warn("Please enter valid operation");
            quizOperations();
            choice = CorrectInput.getInteger();
        }
        return choice;
    }

    public void quizOperations() {
        QuizModuleTask.operations.forEach((operationCode, description) ->
                LOGGER.info("Press {} > for {}", operationCode, description)
        );
    }
}

