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

import com.epam.dto.QuestionDto;
import com.epam.service.admin.QuestionService;

@ExtendWith(MockitoExtension.class)
class QuestionRestControllerTest {

	@Mock
	private QuestionRestController restController;

	@Mock
	private QuestionService questionService;

	QuestionDto qDto1 = new QuestionDto();

	@BeforeEach
	void setUp() {
		restController = new QuestionRestController();
		restController.questionService = questionService;
	}

	@Test
	void testAll() {

		ResponseEntity<List<QuestionDto>> responseEntity = restController.all();
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testGetOneQuestion() {
		ResponseEntity<QuestionDto> responseEntity = restController.newQuestion(qDto1);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void addQuestion() {

		ResponseEntity<QuestionDto> responseEntity = restController.oneQuestion(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void updateQuestion() {

		ResponseEntity<QuestionDto> responseEntity = restController.updateQuestion(qDto1);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void deleteQuestion() {

		ResponseEntity<String> responseEntity = restController.deleteQuestion(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
