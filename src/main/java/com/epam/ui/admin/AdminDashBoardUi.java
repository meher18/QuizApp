package com.epam.ui.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.service.admin.AdminDash;
import com.epam.ui.admin.question.QuestionModuleUi;
import com.epam.ui.admin.quiz.QuizModuleUi;
import com.epam.util.CorrectInput;

@Component
public class AdminDashBoardUi {
	public static final Logger LOGGER = LogManager.getLogger(AdminDashBoardUi.class);

	@Autowired
	QuizModuleUi quizModuleUi;

	@Autowired
	QuestionModuleUi questionModuleUi;

	public int takeInputForAdminModule() {
		String heading = "-".repeat(10) + " Admin Dashboard " + "-".repeat(10);
		LOGGER.info(heading);
		LOGGER.info("- Choose Below Operations");
		LOGGER.info("Press 1 > QUESTION MODULE");
		LOGGER.info("Press 2 > QUIZ MODULE");
		LOGGER.info("Press 3 > TO LOGOUT");
		return CorrectInput.getInteger();
	}

	public void showAdminTasks() {

		int choice = takeInputForAdminModule();
		switch (choice) {
		case 1: {
			questionModuleUi.performQuestionOperation();
		}
			break;
		case 2: {
			quizModuleUi.showQuizModuleTasks();
		}
			break;
		case 3: {
			if (AdminDash.logoutAdmin()) {
				LOGGER.info("you logged out ...");
				App.startApplication();
			} else {
				LOGGER.info("Unable to log out ...");
			}
		}
			break;
		default: {
			showAdminTasks();
		}
		}
	}

}
