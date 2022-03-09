package com.epam.ui.admin.quiz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.Quiz;
import com.epam.global.CorrectInput;
import com.epam.service.admin.questionservice.QuestionService;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.ui.admin.AdminDashBoardUi;
import com.epam.ui.admin.question.QuestionBuilderUi;
import com.epam.ui.viewer.QuizViewer;

@Component
public class QuizBuilderUi {


    public static final Logger LOGGER = LogManager.getLogger(QuizBuilderUi.class);
    // Quiz builder service
    @Autowired
    QuizService quizService;

    @Autowired
    Quiz quiz;

    public void buildQuiz() {
        quiz = takeInputForQuiz();
        selectQuestionsAndAdd();
    }

    public Quiz takeInputForQuiz() {
        LOGGER.info("Enter the quiz name: ");
        String quizName = CorrectInput.getString();
        Quiz quizObj = AppContext.getApplicationContext().getBean(Quiz.class);
        quizObj.setQuizName(quizName);
        return quizObj;
    }

    public void selectQuestionsAndAdd() {

        int noOfQuestions = AppContext.getApplicationContext().getBean(QuestionService.class).getQuestions().size();
        if (noOfQuestions <= 0) {
            LOGGER.info("No questions to select");
            nextAfterNoQuestions();
        } else {

            SelectQuestionInput selectQuestionInput = AppContext.getApplicationContext().getBean(SelectQuestionInput.class);

            int questionId = selectQuestionInput.questionSelectionInput();

            boolean isQuestionAdded = quizService.selectQuestionAndAddToQuiz(quiz, questionId);
            if (isQuestionAdded) {
                LOGGER.info("Question Added ");
                nextAfterAddingQuestion();
            }
        }
        QuizRedirectUi.redirect();
    }


    public void nextAfterAddingQuestion() {

        LOGGER.info("Press 1 > ADD ANOTHER QUESTION TO QUIZ");
        LOGGER.info("Press 2 > SAVE QUIZ");
        LOGGER.info("Press 3 > ADMIN DASHBOARD");


        int nextChoice = CorrectInput.getInteger();
        if (nextChoice == 1) {
            selectQuestionsAndAdd();
        } else if (nextChoice == 2) {
            // if choice == 3, save the quiz
            boolean isQuizSaved = quizService.saveQuiz(quiz);
            if (isQuizSaved) {
                LOGGER.info("Quiz Saved");
                nextAfterSavingQuiz();
            } else {
                LOGGER.error("Unable to save the quiz");
            }
        } else {
            AdminDashBoardUi.showAdminTasks();
        }
    }

    public void nextAfterNoQuestions() {

        LOGGER.info("Press 1 > ADD QUESTIONS");
        LOGGER.info("Press 2 > ADMIN DASHBOARD");

        int choice = CorrectInput.getInteger();
        if (choice == 1) {
            AppContext.getApplicationContext().getBean(QuestionBuilderUi.class).buildQuestion();
        } else if (choice == 2) {
            AdminDashBoardUi.showAdminTasks();
        }
    }

    public void nextAfterSavingQuiz() {

        LOGGER.info("Press 1 > VIEW ALL QUIZZES");
        LOGGER.info("Press 2 > ADMIN DASHBOARD");
        int choice = CorrectInput.getInteger();
        if (choice == 1) {
            QuizViewer.viewAllQuizzes();
        } else if (choice == 2) {
            AdminDashBoardUi.showAdminTasks();
        }
    }
}
