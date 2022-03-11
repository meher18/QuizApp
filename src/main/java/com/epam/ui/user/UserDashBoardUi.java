package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.service.user.UserDash;
import com.epam.ui.user.quiz.UserQuizUi;
import com.epam.ui.viewer.UserQuizViewer;
import com.epam.util.CorrectInput;

@Component
public class UserDashBoardUi {

	public static final Logger LOGGER = LogManager.getLogger(UserDashBoardUi.class);

	@Autowired
	UserQuizUi userQuizUi;

	@Autowired
	UserQuizViewer userQuizViewer;

	public int takeInputForUserTasks() {
		String heading = "-".repeat(10) + " User Dashboard " + "-".repeat(10);
		LOGGER.info(heading);
		LOGGER.info("Choose below operations");
		LOGGER.info("Press 1 > TAKE QUIZ");
		LOGGER.info("Press 2 > YOUR PARTICIPATION");
		LOGGER.info("Press 3 > TO LOGOUT");
		return CorrectInput.getInteger();
	}

	public void assignTasks() {
		int choice = takeInputForUserTasks();
		switch (choice) {
		case 1: {
		
			userQuizUi.takeQuiz(UserSession.getLoggedUser());
		}
			break;
		case 2: {
		
			userQuizViewer.viewAllUserQuizzes();
			UserRedirectUi.redirect();
		}
			break;
		case 3: {
			if (UserDash.logoutUser()) {
				UserSession.setLoggedUser(null);
				LOGGER.info("you logged out ...");
				App.startApplication();
			} else {
				LOGGER.info("Unable to log out ...");
			}
		}
			break;
		default: {
			LOGGER.warn("Invalid Input");
			assignTasks();
			}
		}
	}

}
