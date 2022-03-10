package com.epam.ui.admin.quiz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.global.CorrectInput;
import com.epam.ui.viewer.QuestionsViewer;

@Component
public class SelectQuestionInput {
	
	@Autowired
	QuestionsViewer questionsViewer;

	public static final Logger LOGGER = LogManager.getLogger(SelectQuestionInput.class);

	public SelectQuestionInput() {
	}

	public Integer questionSelectionInput() {
		
		questionsViewer.viewQuestions();
		LOGGER.info("Select the index of the question: ");
		return CorrectInput.getInteger();
	}
}
