package com.epam.ui.user.quiz;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.entity.Question;
import com.epam.entity.QuestionAnswer;
import com.epam.global.CorrectInput;

@Component
public class StartQuizUi {

	public static final Logger LOGGER = LogManager.getLogger(StartQuizUi.class);

	@Autowired
	@Qualifier("questionAnswers")
	List<QuestionAnswer> answers;
	
	@Autowired
	QuestionAnswer userAnswer;

	public List<QuestionAnswer> startQuiz(Map<Integer, Question> questions) {

		String heading = "****".repeat(10) + " Quiz Started ! " + "****".repeat(10);
		LOGGER.info(heading);

		LOGGER.info("Below are the questions -> ");

		questions.forEach((questionId, question) -> {

			String questionHeading = question.getQuestionTitle() + " ( mark - " + question.getMark() + " )";

			LOGGER.info(questionHeading);

			question.getOptions().forEach(option -> LOGGER.info(option.getOptionTitle()));

			LOGGER.info("Choose correct option: ");
			int selectedIndex = CorrectInput.getInteger();

			userAnswer.setQuestion(question);
			userAnswer.setOriginalAnswer(question.getAnswer());
			userAnswer.setSelectedAnswer(selectedIndex);
			answers.add(userAnswer);
			
		});
		LOGGER.info("QUIZ FINISHED ...");
		return answers;
	}
}
