package com.epam.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.entity.Quiz;
import com.epam.service.libraryservice.QuestionsLibraryService;
import com.epam.service.libraryservice.QuizLibraryService;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

	@Mock
	private QuizService quizService;

	@Mock
	private QuestionsLibraryService questionLibraryService;

	@Mock
	private QuizLibraryService quizLibraryService;

	Map<Integer, Question> questions = new HashMap<>();
	Question ques1 = new Question();
	Question ques2 = new Question();
	QuestionOption questionOption = new QuestionOption();
	Map<Integer, Quiz> quizzes = new HashMap<>();
	Quiz q1 = new Quiz();
	Quiz q2 = new Quiz();

	Map<Integer, QuizDto> quizDtos = new HashMap<>();
	QuizDto quizDto1 = new QuizDto();
	QuizDto quizDto2 = new QuizDto();

	@BeforeEach
	void init() {

		quizDto1.setId(1);
		quizDto2.setId(2);

		questionOption.setId(0);
		questionOption.setTitle("asdfasdf");

		ques1.setId(1);
		ques1.setOption(questionOption);

		ques2.setId(2);
		ques2.setOption(questionOption);

		questions.put(1, ques1);
		questions.put(2, ques2);
		q1.setId(1);
		quizzes.put(1, q1);
		q2.setId(2);
		quizzes.put(2, q2);

		quizDtos.put(1, quizDto1);
		quizDtos.put(2, quizDto2);

		quizService = new QuizService();
		quizService.questionsLibrary = questionLibraryService;
		quizService.quizLibrary = quizLibraryService;
		quizService.mapper = new ModelMapper();
	}

	@Test
	void testGetAllQuizzes() {
		when(quizLibraryService.getQuizzes()).thenReturn(quizzes);
		assertEquals(2, quizService.getAllQuizzes().size());
	}

	@Test
	void testGetQuestionForQuiz() {
		when(quizLibraryService.getQuestionsForQuiz(q1)).thenReturn(questions);
		assertEquals(2, quizService.getQuestionsForQuiz(q1).size());
	}

	@Test
	void testGetQuiz() {
		when(quizLibraryService.getQuiz(1)).thenReturn(q1);
		assertNotNull(quizService.getQuiz(1));
	}

	@Test
	void testCreateQuiz() {

		when(questionLibraryService.getQuestions()).thenReturn(questions);
		when(quizLibraryService.saveOrEdit(any())).thenReturn(q1);
		assertNotNull(quizService.createQuiz(quizDto1));
	}

	@Test
	void testDelete() {
		when(quizLibraryService.deleteQuiz(anyInt())).thenReturn(true);
		assertTrue(quizService.delete(1));
	}

	@Test
	void testHostQuiz() {
		when(quizService.getAllQuizzes()).thenReturn(quizDtos);
		when(quizLibraryService.changeQuizStatus(anyInt(), anyString())).thenReturn(true);
		assertNotNull(quizService.hostQuiz(1));
	}

	@Test
	void testUpdate() {
		when(quizLibraryService.getQuiz(1)).thenReturn(q1);
		when(quizLibraryService.saveOrEdit(any())).thenReturn(q1);
		assertNotNull(quizService.update(quizDto1));
	}
}
