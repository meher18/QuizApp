package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.dto.QuizDto;
import com.epam.service.admin.QuizService;

@ExtendWith(MockitoExtension.class)
class QuizRestControllerTest {

	@Mock
	private QuizRestController restController;

	@Mock
	private QuizService quizService;

	QuizDto qDto1 = new QuizDto();

	@BeforeEach
	void setUp() {
		restController = new QuizRestController();
		restController.quizService = quizService;
	}

	@Test
	void testAll() {

		ResponseEntity<List<QuizDto>> responseEntity = restController.all();
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testGetOneQuestion() {
		String[] questions = new String[] { "1", "2" };
		ResponseEntity<QuizDto> responseEntity = restController.newQuiz(qDto1, questions);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void addQuestion() {

		ResponseEntity<QuizDto> responseEntity = restController.oneQuiz(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void updateQuestion() {
		String[] questions = new String[] { "1", "2" };
		ResponseEntity<QuizDto> responseEntity = restController.updateQuiz(qDto1, questions);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void deleteQuestion() {

		ResponseEntity<String> responseEntity = restController.deleteQuiz(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
