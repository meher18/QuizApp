package com.epam.ui.admin.quiz;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.entity.Quiz;
import com.epam.global.Constants;
import com.epam.global.CorrectInput;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.exceptions.InValidQuizId;

@Component
public class QuizModifyUi {

	public static final Logger LOGGER = LogManager.getLogger(QuizModifyUi.class);

	@Autowired
	QuizService quizService;

	@Autowired
	@Qualifier("quizUpdateOperations")
	Map<Integer, QuizOperationUi> updateFunctions;

	@Autowired
	SelectQuestionInput selectQuestionInput;

	Quiz quiz;

	public void updateQuizTitle() {
		LOGGER.info("Enter new title for quiz");
		String name = CorrectInput.getString();
		quiz.setQuizName(name);
	}

	public void addQuestionToQuiz() {

		int questionId = selectQuestionInput.questionSelectionInput();

		if (!quizService.getQuestionForQuiz(quiz).containsKey(questionId)) {
			quizService.selectQuestionAndAddToQuiz(quiz, questionId);
		} else {
			LOGGER.warn("Quiz already contains this question");
		}
	}

	public void deleteQuestionFromQuiz() {
		LOGGER.info("Enter the question id to delete");
		int questionId = CorrectInput.getInteger();

		if (quizService.getQuestionForQuiz(quiz).containsKey(questionId)) {
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
			quizService.validateCode(code);
			quiz = quizService.getQuiz(code);
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

		boolean isQuizModified = quizService.update(quiz, quiz.getId());
		if (isQuizModified) {
			LOGGER.info("Quiz Modified");
			update();
		} else {

			LOGGER.error("Quiz not modified");
		}
	}

	public int quizUpdateOperations() {
		quizOperations();
		int choice = CorrectInput.getInteger();
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
		QuizUpdateOperation.operations
				.forEach((operationCode, description) -> LOGGER.info("Press {} > for {}", operationCode, description));
	}

}
