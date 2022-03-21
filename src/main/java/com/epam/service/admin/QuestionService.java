package com.epam.service.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.exceptions.InValidQuestionId;
import com.epam.service.libraryservice.QuestionsLibraryService;
import com.epam.util.Constants;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionService {

	@Autowired
	QuestionsLibraryService questionsLibrary;

	@Autowired
	ModelMapper mapper;

	Map<Integer, Question> questions;

	public Map<Integer, QuestionDto> getQuestions() {

		return questionsLibrary.getQuestions().values().stream()
				.collect(Collectors.toMap(Question::getId, value -> {
					QuestionDto qDto = mapper.map(value, QuestionDto.class);
					qDto.setQuestionOptions(value.getOptions().stream().map(option -> option.getOptionTitle()).collect(Collectors.toList()));
					return qDto;
				}));
	}

	public QuestionDto getQuestion(int questionId) {

		Question question = questionsLibrary.getQuestion(questionId);
		QuestionDto questionDto = mapper.map(question, QuestionDto.class);
		return questionDto;
	}


	public void validateQuestionId(int questionId) throws InValidQuestionId, InValidQuestionDeletion {

		if (!getQuestions().containsKey(questionId)) {

			throw new InValidQuestionId(Constants.INVALID_QUESTION_ID);
		}
		if (questionsLibrary.getNoQuizzesForQuestionId(questionId) > 0) {
			throw new InValidQuestionDeletion(Constants.INVALID_QUESTION_DELETION);
		}
	}

	public boolean createQuestion(QuestionDto newQuestionDto) {
		
		Question newQuestion = mapper.map(newQuestionDto, Question.class);
		newQuestion.setOptions(newQuestionDto.getQuestionOptions().stream().map(o->{
			QuestionOption option = new QuestionOption();
			option.setOptionTitle(o);
			return option;
		}).collect(Collectors.toList()));
// question Creator
		boolean isQuestionCreated = false;

		if (newQuestion != null) {
			// this id is used when we are implementing collections as db
			int questionId = 0;
			boolean isAdded = questionsLibrary.addQuestion(questionId, newQuestion);
			if (isAdded) {
				isQuestionCreated = true;
			}
		}
		return isQuestionCreated;
	}

	public boolean update(QuestionDto questionDto) {

		int id = questionDto.id;
		Question question = questionsLibrary.getQuestion(id);
		question.setQuestionTitle(questionDto.questionTitle);

		List<String> optionsList = questionDto.questionOptions;

		question.questionOptions.clear();

		for (String optionTitle : optionsList) {
			QuestionOption option = new QuestionOption();
			option.setOptionTitle(optionTitle);
			question.setOption(option);

		}
		question.setTopicTag(questionDto.topicTag);
		question.setDifficultyTag(questionDto.difficultyTag);
		question.setAnswer(questionDto.answer);
		question.setMark(questionDto.mark);
		boolean isQuestionUpdated = false;

		if (question != null) {
			isQuestionUpdated = questionsLibrary.editQuestion(id, question);
		}
		return isQuestionUpdated;
	}

	public boolean delete(int questionId) {

		return questionId > 0 && questionsLibrary.deleteQuestion(questionId);
	}
}
