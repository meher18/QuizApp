package com.epam.ui.admin.quiz;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.Quiz;
import com.epam.global.Constants;
import com.epam.global.CorrectInput;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.exceptions.InValidQuizId;

@Component
public class QuizModifyUi {

    public static final Logger LOGGER = LogManager.getLogger(QuizModifyUi.class);

    @Autowired
    QuizService quizModifier;

    static Map<Integer, QuizOperationUi> updateFunctions = new HashMap<>();

    Quiz quiz;

    static {
        updateFunctions.put(1, () -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).updateQuizTitle());
        updateFunctions.put(2, () -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).addQuestionToQuiz());
        updateFunctions.put(3, () -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).deleteQuestionFromQuiz());
        updateFunctions.put(4, () -> AppContext.getApplicationContext().getBean(QuizModuleUi.class).showQuizModuleTasks()
        );
    }

    public void updateQuizTitle() {
        LOGGER.info("Enter new title for quiz");
        String name = CorrectInput.getString();
        quiz.setQuizName(name);
    }

    public void addQuestionToQuiz() {

        int questionId = AppContext.getApplicationContext().getBean(SelectQuestionInput.class).questionSelectionInput();

        if (!QuizService.getQuestionForQuiz(quiz).containsKey(questionId)) {
            AppContext.getApplicationContext().getBean(QuizService.class).selectQuestionAndAddToQuiz(quiz, questionId);
        } else {
            LOGGER.warn("Quiz already contains this question");
        }
    }

    public void deleteQuestionFromQuiz() {
        LOGGER.info("Enter the question id to delete");
        int questionId = CorrectInput.getInteger();

        if (QuizService.getQuestionForQuiz(quiz).containsKey(questionId)) {
            quiz.getQuestions().remove(questionId);
        } else {
            LOGGER.error("InValid Question ID, Question with this question Id is not a part of quiz");
            deleteQuestionFromQuiz();
        }
    }

    // starting here
    public void updateQuiz() {
        int code = takeQuizCodeForModification();
        try {
            quizModifier.validateCode(code);
            quiz = QuizService.getQuiz(code);
            update();
        } catch (InValidQuizId e) {
            LOGGER.error(Constants.INVALID_QUIZ_ID);
            QuizRedirectUi.redirect();
        }
        // quiz from db

    }

    public void update() {
        int choice = quizUpdateOperations();
        updateFunctions.get(choice).operation();
        boolean isQuizModified = quizModifier.update(quiz, quiz.getId());
        if (isQuizModified) {
            LOGGER.info("Quiz Modified");
            update();
        } else {

            LOGGER.error("Quiz not modified");
        }
    }

    public int quizUpdateOperations() {
        int choice = 0;
        quizOperations();
        choice = CorrectInput.getInteger();
        while (!QuizUpdateOperation.operations.containsKey(choice)) {
            LOGGER.warn("Please enter valid operation");
            quizOperations();
            choice = CorrectInput.getInteger();
        }
        return choice;
    }

    public int takeQuizCodeForModification() {
        LOGGER.info("Enter Quiz Code for modification: ");
        return CorrectInput.getInteger();
    }

    public void quizOperations() {
        QuizUpdateOperation.operations.forEach((operationCode, description) -> LOGGER.info("Press {} > for {}", operationCode, description));
    }

}
