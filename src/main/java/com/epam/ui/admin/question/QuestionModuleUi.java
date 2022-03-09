package com.epam.ui.admin.question;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.global.CorrectInput;

@Component
public class QuestionModuleUi {

    public static final Logger LOGGER = LogManager.getLogger(QuestionModuleUi.class);


    static Map<Integer, QuestionOperationUi> questionModuleUiOperations;


    private QuestionModuleUi() {
    }

    @Autowired
    @Qualifier("questionServiceUiFunctions")
    public void setQuestionServiceUiFunctions(Map<Integer, QuestionOperationUi> questionModuleUiOperations)
    {
        QuestionModuleUi.questionModuleUiOperations = questionModuleUiOperations;
    }

    public static void performQuestionOperation() {
        int choice = takeInputForModuleTask();
        questionModuleUiOperations.get(choice).questionOperation();
    }

    public static int takeInputForModuleTask() {
        int choice = 0;
        String heading = "-".repeat(10) + " Admin Dashboard / Question Module " + "-".repeat(10);
        LOGGER.info(heading);
        questionOperations();
        choice = CorrectInput.getInteger();
        while (!QuestionModuleTask.operations.containsKey(choice)) {
            LOGGER.warn("Please enter valid operation");
            questionOperations();
            choice = CorrectInput.getInteger();
        }
        return choice;
    }

    private static void questionOperations() {
        QuestionModuleTask.operations.forEach((operationCode, description) ->
            LOGGER.info("Press {} > {}",operationCode, description)
        );
    }
}
