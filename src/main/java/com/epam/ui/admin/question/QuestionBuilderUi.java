package com.epam.ui.admin.question;

import java.util.Arrays;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.service.admin.questionservice.QuestionService;
import com.epam.util.CorrectInput;

@Component
public class QuestionBuilderUi {

    public static final Logger LOGGER = LogManager.getLogger(QuestionBuilderUi.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    Question question;
    
    @Autowired
    QuestionModuleUi  questionModuleUi;

    public void buildQuestion() {

        Question newQuestion = takeInputForQuestion();
        boolean isQuestionCreated = questionService.createQuestion(newQuestion);
        if (isQuestionCreated) {
            LOGGER.info("Question Added");
            takeNextInput();
        } else {
            LOGGER.error("Unable to Add Question");
            QuestionRedirectUi.redirect();
        }
    }

    public Question takeInputForQuestion() {
        question = AppContext.getApplicationContext().getBean(Question.class);
        setHeading();
        setOptions();
        setTopicTag();
        setDifficultyTag();
        setAnswer();
        setMark();
        return question;
    }

    public void setHeading() {
        LOGGER.info("\n Enter the Question title - ");
        String questionHeading = CorrectInput.getString();
        question.setQuestionTitle(questionHeading);
    }


    public void setOptions() {

        LOGGER.info("Enter number of options for question");
        int noOfOptions = CorrectInput.getInteger();
        if (noOfOptions >= 2 && noOfOptions <= 6) {
            for (int i = 1; i <= noOfOptions; i++) {
                LOGGER.info("Enter Option {}", i);
                String optionHeading = CorrectInput.getString();

                QuestionOption questionOption = AppContext.getApplicationContext()
                        .getBean(QuestionOption.class);
                questionOption.setOptionTitle(optionHeading);
                question.setOption(questionOption);
            }
        } else {
            LOGGER.warn("Please add min 2 and max 6 options");
            setOptions();
        }
    }

    public void setTopicTag() {
        LOGGER.info("Enter a topic tag for question: ");
        String topicTag = CorrectInput.getString();
        question.setTopicTag(topicTag);
    }

    public void setDifficultyTag() {
        LOGGER.info("Enter the question difficulty tag (EASY / MEDIUM / HARD): ");
        String difficultyTag = CorrectInput.getString();
        if (Arrays.asList("EASY", "MEDIUM", "HARD").contains(difficultyTag.toUpperCase(Locale.ROOT))) {
            question.setDifficultyTag(difficultyTag);
        } else {
            LOGGER.warn("Wrong difficulty tag entered");
            setDifficultyTag();
        }
    }

    public void setAnswer() {
        int answer = getAnswer();
        if (answer >= 1 && answer <= question.getOptions().size()) {
            question.setAnswer(answer);
        } else {
            LOGGER.warn("Invalid option answer, Please enter only between ( 1 - {} ) ", question.getOptions().size());
            setAnswer();
        }
    }

    public int getAnswer() {
        LOGGER.info("Enter the answer of the question (option number): ");
        return CorrectInput.getInteger();
    }

    public void setMark() {
        int mark = getMark();
        if (mark >= 1 && mark <= 100) {
            question.setMark(mark);
        } else {
            LOGGER.warn("Please enter the marks between (1 - 100)");
            setMark();
        }
    }

    public int getMark() {
        LOGGER.info("Enter mark for the question (1 - 100): ");
        return CorrectInput.getInteger();
    }

    public void takeNextInput() {
        LOGGER.info("Enter the below options ->");
        LOGGER.info("Press 1 > ADD ONE MORE QUESTION");
        LOGGER.info("Press 2 > QUESTION MODULE");
        int choice = CorrectInput.getInteger();
        if (choice == 1) {
            buildQuestion();
        } else {
        	AppContext.getApplicationContext().getBean(QuestionModuleUi.class).performQuestionOperation();
        }
    }
}
