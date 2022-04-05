package com.epam.service.admin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.service.libraryservice.QuestionsLibraryService;
import com.epam.util.Constants;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	QuestionsLibraryService questionsLibrary;

	public Map<Integer, QuestionDto> getQuestions() {

		return questionsLibrary.getQuestions().values().stream()
				.collect(Collectors.toMap(Question::getId, value -> mapper.map(value, QuestionDto.class)));
	}
	
	public QuestionDto getQuestion(int questionId) {
		return mapper.map(questionsLibrary.getQuestion(questionId), QuestionDto.class);
	}

	public QuestionDto save(Question newQuestion) {
		Question addedQuestion = questionsLibrary.saveOrEdit(newQuestion);
		return mapper.map(addedQuestion, QuestionDto.class);
	}

	public QuestionDto create(QuestionDto newQuestionDto) {

		Question newQuestion = mapper.map(newQuestionDto, Question.class);
		return save(newQuestion);

	}

	public QuestionDto update(QuestionDto questionDto) {

		int id = questionDto.getId();
		Question question = questionsLibrary.getQuestion(id);
		question.setQuestionTitle(questionDto.getQuestionTitle());

		List<QuestionOption> optionsList = mapper.map(questionDto.getQuestionOptions(), new TypeToken<List<QuestionOption>>() {}.getType());

		question.questionOptions.clear();

		for (QuestionOption option : optionsList) {
			question.setOption(option);
		}
		question.setTopicTag(questionDto.getTopicTag());
		question.setDifficultyTag(questionDto.getDifficultyTag());
		question.setAnswer(questionDto.getAnswer());
		question.setMark(questionDto.getMark());

		return save(question);
	}

	public void validateQuestionId(int questionId) {

		if (questionsLibrary.getNoQuizzesForQuestionId(questionId) > 0) {
			throw new InValidQuestionDeletion(Constants.INVALID_QUESTION_DELETION);
		}
	}

	public boolean delete(int questionId) {

		validateQuestionId(questionId);

		return questionsLibrary.deleteQuestion(questionId);
	}
}
