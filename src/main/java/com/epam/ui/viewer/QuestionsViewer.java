package com.epam.ui.viewer;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.service.admin.questionservice.QuestionService;

@Component
public class QuestionsViewer {

    public static final Logger LOGGER = LogManager.getLogger(QuestionsViewer.class);

    public void viewQuestions() {
    	
        QuestionService questionService = AppContext.getApplicationContext().getBean(QuestionService.class);
        Map<Integer, Question> questionMap = questionService.getQuestions();
        if (questionMap.isEmpty()) {
            LOGGER.info("- No Questions Present...");
        } else {
            printAll(questionMap);
        }
    }

    public void printAll(Map<Integer, Question> questionMap) {
        LOGGER.info("Below are the questions -> ");
        questionMap.forEach((key, questionObj) -> {
            LOGGER.info("-------------------------------------------------------");
            LOGGER.info(" Question Code: {}", key);
            LOGGER.info(" Question Title: [ {} ]", questionObj.getQuestionTitle());
            LOGGER.info(" Below are the options ->");

            questionObj.getOptions().forEach(option -> {
                if (questionObj.getAnswer()-1 == questionObj.getOptions().indexOf(option)) {
                    LOGGER.info("    Answer - [ {} ] ", option.getOptionTitle());
                } else {
                    LOGGER.info("             [ {} ]", option.getOptionTitle());
                }
            });
        });
    }
}
