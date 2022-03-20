package com.epam.service.admin.questionservice;

import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

//    	Map<Integer, QuestionDto> questionDtos = questionsLibrary.getQuestions()
//    			.values().stream().collect(Collectors.toMap(K -> K.getId(), V -> {
//    				question
//    			}));

//		mapper.typeMap(Question.class, QuestionDto.class).addMappings(mapper -> {
//				mapper.map(src -> src.getOptions().stream().map( option -> option.getOptionTitle()).collect(Collectors.toList()), QuestionDto::setQuestionOptions);
//			});
		mapper.typeMap(Question.class, QuestionDto.class).addMappings( new PropertyMap<Question, QuestionDto>() {
			
			@Override
			protected void configure() {
				map().setQuestionOptions(source.getOptions().stream().map(o -> o.getOptionTitle()).collect(Collectors.toList()));
				
			}
		} );
//		
		System.out.println("mapping done");
		
//   
		Map<Integer, QuestionDto> questionDtos = questionsLibrary.getQuestions().values().stream()
				.collect(Collectors.toMap(K -> K.getId(), V -> mapper.map(V,QuestionDto.class)));
		return questionDtos;
	}

	public Question getQuestion(int questionId) {

		return questionsLibrary.getQuestion(questionId);
	}

	public QuestionDto getQuestionDto(int questionId) {

		ModelMapper mapper = new ModelMapper();
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

	public boolean createQuestion(Question newQuestion) {
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

	public boolean update(Question newQuestion, int id) {

		boolean isQuestionUpdated = false;

		if (newQuestion != null) {
			isQuestionUpdated = questionsLibrary.editQuestion(id, newQuestion);
		}
		return isQuestionUpdated;
	}

	public boolean delete(int questionId) {

		return questionId > 0 && questionsLibrary.deleteQuestion(questionId);
	}
}
