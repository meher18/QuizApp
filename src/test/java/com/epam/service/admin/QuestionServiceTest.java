package com.epam.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.exceptions.InValidQuestionId;
import com.epam.service.libraryservice.QuestionsLibraryService;
import com.epam.util.Constants;

@ExtendWith(SpringExtension.class)
class QuestionServiceTest {

	@Mock
	QuestionService questionService;

	@Mock
	QuestionsLibraryService libraryService;

	Map<Integer, Question> questions = new HashMap<>();
	Question q1 = new Question();
	Question q2 = new Question();

	QuestionDto qDto1 = new QuestionDto();
	QuestionDto qDto2 = new QuestionDto();

	QuestionOption questionOption = new QuestionOption();
	
	@BeforeEach
	void init() {

		
		questionOption.setId(0);
		questionOption.setTitle("asdfasdf");

		q1.setId(1);
		q1.setOption(questionOption);

		q2.setId(2);
		q2.setOption(questionOption);

		questions.put(1, q1);
		questions.put(2, q2);

		questionService = new QuestionService();
		questionService.questionsLibrary = libraryService;
		questionService.mapper = new ModelMapper();
	}

	@Test
	void testGetQuestions() {

		when(libraryService.getQuestions()).thenReturn(questions);
		assertEquals(2, questionService.getQuestions().size());
	}

	@Test
	void testGetQuestion() {

		when(libraryService.getQuestion(1)).thenReturn(q1);
		assertNotNull(questionService.getQuestion(1));
	}



	@Test
	void testValidateQuestionId() {
		when(libraryService.getQuestions()).thenReturn(questions);
		when(libraryService.getNoQuizzesForQuestionId(1)).thenReturn(0);
		InValidQuestionId thrown = assertThrows(
				InValidQuestionId.class,
                () -> questionService.validateQuestionId(-1),
                Constants.INVALID_QUESTION_ID
        );

        assertEquals(Constants.INVALID_QUESTION_ID, thrown.getMessage());
	}
	@Test
	void testValidateQuestionIdForInValidDeletion() {
		when(libraryService.getQuestions()).thenReturn(questions);
		when(libraryService.getNoQuizzesForQuestionId(1)).thenReturn(10);
		InValidQuestionDeletion thrown = assertThrows(
				InValidQuestionDeletion.class,
				() -> questionService.validateQuestionId(1),
				Constants.INVALID_QUESTION_DELETION
				);
		
		assertEquals(Constants.INVALID_QUESTION_DELETION, thrown.getMessage());
	}

	@Test
	void testCreateQuestion() {

		qDto1.setMark(3);
		qDto1.setDifficultyTag("adsf");
		qDto1.setTopicTag("afds");
		qDto1.setId(1);
		qDto1.setQuestionTitle("adsfasdf");
		qDto1.setAnswer(3);
		List<String> options = new ArrayList<String>(Arrays.asList(new String[] {"adsf","adsfasdf"}));
		qDto1.setQuestionOptions(options);
		
		when(libraryService.addQuestion(any())).thenReturn(q1);
		assertNotNull(questionService.createQuestion(qDto1));
	}

	@Test
	void testUpdate() {
		qDto1.setMark(3);
		qDto1.setDifficultyTag("adsf");
		qDto1.setTopicTag("afds");
		qDto1.setId(1);
		qDto1.setQuestionTitle("adsfasdf");
		qDto1.setAnswer(3);
		List<String> options = new ArrayList<String>(Arrays.asList(new String[] {"adsf","adsfasdf"}));
		qDto1.setQuestionOptions(options);
		
		when(libraryService.getQuestion(1)).thenReturn(q1);
		when(libraryService.editQuestion(any())).thenReturn(q1);
		assertNotNull(questionService.update(qDto1, 1));
	}

	@Test
	void testDelete() {
		questionService.delete(1);
		verify(libraryService, times(1)).deleteQuestion(anyInt());
	}
	@Test
	void testDeleteForInvalidQuestion() {
		questionService.delete(-111);
		verify(libraryService, times(1)).deleteQuestion(anyInt());
	}

}
