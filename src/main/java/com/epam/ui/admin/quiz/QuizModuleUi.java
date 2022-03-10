package com.epam.ui.admin.quiz;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.global.CorrectInput;

@Component
public class QuizModuleUi {

	public static final Logger LOGGER = LogManager.getLogger(QuizModuleUi.class);

	@Autowired
	@Qualifier("quizModuleOperations")
	Map<Integer, QuizOperationUi> questionServiceUiFunctions;

	public void showQuizModuleTasks() {
		int choice = takeInputForModuleTask();
		questionServiceUiFunctions.get(choice).operation();
	}

	public int takeInputForModuleTask() {
		
		quizOperations();
		int choice = CorrectInput.getInteger();
		while (!QuizModuleTask.operations.containsKey(choice)) {
			LOGGER.warn("Please enter valid operation");
			quizOperations();
			choice = CorrectInput.getInteger();
		}
		return choice;
	}

	public void quizOperations() {
		QuizModuleTask.operations
				.forEach((operationCode, description) -> LOGGER.info("Press {} > for {}", operationCode, description));
	}
}
