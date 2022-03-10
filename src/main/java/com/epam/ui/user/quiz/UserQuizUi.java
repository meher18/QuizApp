package com.epam.ui.user.quiz;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.entity.QuestionAnswer;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;
import com.epam.global.Constants;
import com.epam.global.CorrectInput;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.exceptions.InValidQuizId;
import com.epam.service.user.UserQuizService;
import com.epam.ui.admin.Utility;
import com.epam.ui.user.UserRedirectUi;
import com.epam.ui.user.UserSession;
import com.epam.ui.viewer.UserQuizViewer;

@Component
public class UserQuizUi {

	@Autowired
	UserQuizViewer userQuizViewer;

	@Autowired
	UserQuizService userQuizService;

	@Autowired
	QuizService quizService;

	@Autowired
	StartQuizUi startQuizUi;

	public static final Logger LOGGER = LogManager.getLogger(UserQuizUi.class);

	public Integer takeInputForUserQuiz() {
		
		LOGGER.info("Select the quiz index from above quizzes...");
		return CorrectInput.getInteger();
	}

	public void takeQuiz(User userObj) {

		// viewAllQuizzes() shows all quizzes and return a boolean
		if (!userQuizViewer.viewAllQuizzes()) {

			LOGGER.info("No Quizzes Available");
			UserRedirectUi.redirect();

		} else {

			int quizId = takeInputForUserQuiz();

			try {

				quizService.validateCode(quizId);
				// check if the quiz is already taken
				boolean isQuizTaken = userQuizService.checkIfQuizTaken(quizId, UserSession.getLoggedUser());

				if (isQuizTaken) {
					LOGGER.info("You have already taken this quiz");
				} else {

					setUserAndMakeQuiz(userObj);
					takeConfirmationAndStartQuiz(quizId);
				}

			} catch (InValidQuizId e) {
				LOGGER.error(Constants.INVALID_QUIZ_ID);
			}

			UserRedirectUi.redirect();
		}
	}

	private void setUserAndMakeQuiz(User userObj) {
		// change it to session user
		User user = userObj;

		userQuizService.setUser(user);
		userQuizService.setUserQuiz(AppContext.getApplicationContext().getBean(UserQuiz.class));
	}

	public void takeConfirmationAndStartQuiz(int indexOfQuiz) {

		int confirmationFlag = Utility.takeInputForConfirmation();

		if (confirmationFlag == 1) {

			userQuizService.getUserQuiz().setQuizById(indexOfQuiz);

			List<QuestionAnswer> userAnswers = startQuizUi
					.startQuiz(userQuizService.getUserQuiz().getQuiz().getQuestions());
			userQuizService.setAnswers(userAnswers);
			userQuizService.saveQuiz();

		} else {
			UserRedirectUi.redirect();
		}

	}
}
