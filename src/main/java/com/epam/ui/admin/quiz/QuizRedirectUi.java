package com.epam.ui.admin.quiz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.ui.admin.AdminDashBoardUi;
import com.epam.util.CorrectInput;

@Component
public class QuizRedirectUi {
	public static final Logger LOGGER = LogManager.getLogger(QuizRedirectUi.class);

	private QuizRedirectUi() {
	}

	public static void redirect() {
		LOGGER.info("press 1 > QUIZ MODULE");
		LOGGER.info("press 2 > ADMIN MODULE");
		int choice = CorrectInput.getInteger();
		if (choice == 1) {
			QuizModuleUi quizModuleUi = new QuizModuleUi();
			quizModuleUi.showQuizModuleTasks();
		} else {
			AdminDashBoardUi adminDashBoardUi = AppContext.getApplicationContext().getBean(AdminDashBoardUi.class);
			adminDashBoardUi.showAdminTasks();
		}
	}
}
