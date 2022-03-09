package com.epam.ui.admin.quiz;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.global.CorrectInput;
import com.epam.service.admin.questionservice.QuestionService;
import com.epam.ui.viewer.QuestionsViewer;

@Component
public class SelectQuestionInput {

    Map<Integer, Question> questionMap;

    public static final Logger LOGGER = LogManager.getLogger(SelectQuestionInput.class);

    public SelectQuestionInput() {
        questionMap = AppContext.getApplicationContext().getBean(QuestionService.class).getQuestions();
    }

    public Integer questionSelectionInput() {
        AppContext.getApplicationContext().getBean(QuestionsViewer.class).viewQuestions();
        LOGGER.info("Select the index of the question: ");
        return CorrectInput.getInteger();
    }
}
