package com.epam.ui.admin.question;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.config.AppContext;
import com.epam.ui.admin.AdminDashBoardUi;
import com.epam.util.CorrectInput;

public class QuestionRedirectUi {
	public static final Logger LOGGER = LogManager.getLogger(QuestionRedirectUi.class);

	private QuestionRedirectUi() {

	}

	public static void redirect() {
		LOGGER.info("press 1 > to go to question module");
		LOGGER.info("press 2 > to go to admin module");

		int choice = CorrectInput.getInteger();
		if (choice == 1) {
			AppContext.getApplicationContext().getBean(QuestionModuleUi.class).performQuestionOperation();
		} else {
			AppContext.getApplicationContext().getBean(AdminDashBoardUi.class).showAdminTasks();
		}
	}
}
