package com.epam.ui.admin.question;

import java.util.Map;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.entity.Question;
import com.epam.global.CorrectInput;
import com.epam.service.admin.questionservice.QuestionService;

@Component
public class QuestionUpdateUi {

    @Autowired
    QuestionService questionService;

    @Autowired
    @Qualifier("questionUpdateOperations")
    Map<Integer, Consumer<Question>> questionUpdateOperationFunctions;

    public static final Logger LOGGER = LogManager.getLogger(QuestionUpdateUi.class);


    public void updateMark(Question q) {
        LOGGER.info("Enter new mark");
        int mark = CorrectInput.getInteger();
        q.setMark(mark);
    }

    public void updateAnswer(Question q) {
        LOGGER.info("Enter new answer");
        int answer = CorrectInput.getInteger();
        q.setAnswer(answer);
    }

    public void updateDifficulty(Question q) {
        LOGGER.info("Enter new difficulty");
        String difficulty = CorrectInput.getString();
        q.setDifficultyTag(difficulty);
    }

    public void updateTopicTag(Question q) {
        LOGGER.info("Enter new topic tag");
        String topicTag = CorrectInput.getString();
        q.setTopicTag(topicTag);
    }

    public void updateOptions(Question q) {
        if (!q.getOptions().isEmpty()) {
            LOGGER.info("Enter the option no ( 1 - {} )", q.getOptions().size());
            int choice = CorrectInput.getInteger();
            if (choice >= 1 && choice <= q.getOptions().size()) {
                LOGGER.info("Enter new option title");
                String optionTitle = CorrectInput.getString();
                q.getOptions().get(choice - 1).setOptionTitle(optionTitle);
            } else {
                LOGGER.info("Enter valid option number");
                updateOptions(q);
            }
        } else {
            LOGGER.warn("No Options Added");
            QuestionRedirectUi.redirect();
        }
    }

    public void updateTitle(Question q) {
        LOGGER.info("Enter new title");
        String title = CorrectInput.getString();
        q.setQuestionTitle(title);
    }

    public void updateQuestion() {
    	
        int questionId = takeIdForUpdatingQuestion();

        if (questionService.getQuestions().size() <= 0) {
            LOGGER.info("- No Questions added...");
        } else {
            checkAndUpdate(questionId, questionService);
        }
        QuestionRedirectUi.redirect();
    }

    private void checkAndUpdate(int questionId, QuestionService questionService) {
    	
        if (questionService.getQuestions().containsKey(questionId)) {
        	
            //  fetch the question from db to update and persist again
            Question question = questionService.getQuestion(questionId);
            
            int choice = getInputForUpdateFunction();
            
            questionUpdateOperationFunctions.get(choice).accept(question);
            
            // update the question by using the service
            boolean isQuestionUpdated = questionService.update(question, questionId);
            if (isQuestionUpdated) {
                LOGGER.info("Question Updated");
            } else {
                LOGGER.error("Unable to Update");
            }
        } else {
            LOGGER.warn("Invalid question ID");
            updateQuestion();
        }
    }

    private int getInputForUpdateFunction() {
        // show what to update
        showUpdateOperations();
        int choice = CorrectInput.getInteger();
        while (!QuestionUpdateOperation.operations.containsKey(choice)) {
            LOGGER.warn("Enter valid option");
            showUpdateOperations();
            choice = CorrectInput.getInteger();
        }
        return choice;
    }

    public void showUpdateOperations() {
        QuestionUpdateOperation.operations.forEach((choice, function) -> LOGGER.info("{} > {}", choice, function));
    }

    public int takeIdForUpdatingQuestion() {
        LOGGER.info("Enter Question Id for Updating: ");
        return CorrectInput.getInteger();
    }
}
