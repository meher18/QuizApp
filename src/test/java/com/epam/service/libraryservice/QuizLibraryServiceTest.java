package com.epam.service.libraryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@ExtendWith(MockitoExtension.class)
class QuizLibraryServiceTest {

	@Mock
	private QuizLibraryService libraryService;
	@Mock
	private QuizRepository quizRepository;

	Map<Integer, Quiz> quizzes = new HashMap<>();

	Quiz quiz1 = new Quiz();
	Quiz quiz2 = new Quiz();

	@BeforeEach
	void init() {

		
		quiz1.setId(1);
		quiz1.addQuestion(1, new Question());
		quiz2.setId(2);
		quizzes.put(1, quiz1);
		quizzes.put(2, quiz2);

		libraryService = new QuizLibraryService();
		libraryService.quizRepository = quizRepository;
	}

	@Test
	void testGetQuizzes() {
		when(quizRepository.findAll()).thenReturn(new ArrayList<>(quizzes.values()));
		assertEquals(2, libraryService.getQuizzes().size());
	}

	@Test
	void testAddQuiz() {
		when(quizRepository.save(any())).thenReturn(quiz1);
		assertNotNull(libraryService.addQuiz(quiz1));
	}

	@Test
	void testEditQuiz() {
		when(quizRepository.save(any())).thenReturn(quiz1);
		assertNotNull(libraryService.editQuiz(quiz1));
	}

	@Test
	void testDeleteQuiz() {
		libraryService.deleteQuiz(0);
		verify(quizRepository).deleteById(anyInt());
		
	}

	@Test
	void testChangeQuizStatus() {
		when(quizRepository.findById(any())).thenReturn(Optional.ofNullable(quiz1));
		when(quizRepository.save(any())).thenReturn(quiz1);
		assertTrue(libraryService.changeQuizStatus(0, "asdf"));
	}

	@Test
	void testGetQuiz() {
		when(quizRepository.findById(any())).thenReturn(Optional.ofNullable(quiz1));
		assertNotNull(libraryService.getQuiz(0));
	}

	@Test
	void testGetQuestionsForQuiz() {
		when(quizRepository.findById(any())).thenReturn(Optional.ofNullable(quiz1));
		assertEquals(1, libraryService.getQuestionsForQuiz(quiz1).size());
	}

}
