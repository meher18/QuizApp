package com.epam.ui.viewer;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.Quiz;
import com.epam.service.admin.quizservice.QuizService;

@Component
public class QuizViewer {

	public static final Logger LOGGER = LogManager.getLogger(QuizViewer.class);

	@Autowired
	public QuizService quizService;

	public void viewAllQuizzes() {
//		QuizService quizService = AppContext.getApplicationContext().getBean(QuizService.class);
		Map<Integer, Quiz> quizzes = quizService.getAllQuizzes();
		
		if (quizzes.isEmpty()) {
			LOGGER.info("- No Quizzes...");
		} else {
			LOGGER.info("All the quizzes from library ->");
			quizzes.forEach((quizKey, quizObj) -> {
				LOGGER.info("-------------------------------------------------------------");
				LOGGER.info("Quiz Code : {} ", quizKey);
				LOGGER.info("Quiz Name : {} ", quizObj.getQuizName());
				LOGGER.info("Quiz Status: {}", quizObj.getQuizTag());
				LOGGER.info("Questions for these quiz are ->");
				quizObj.getQuestions().forEach((questionKey, question) -> LOGGER
						.info(" [ Question Code: {}, Question Title: {} ] ", questionKey, question.getQuestionTitle()));
			});
		}
	}
}
