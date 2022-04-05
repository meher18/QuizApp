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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.dto.QuestionDto;
import com.epam.exceptions.handler.QuestionExceptionHandler;
import com.epam.service.admin.QuestionService;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class QuestionRestControllerTest {

	private MockMvc mvc;
	@Mock
	private QuestionRestController restController;

	@Mock
	private QuestionService questionService;

	QuestionDto qDto1 = new QuestionDto();

	@BeforeEach
	void setUp() {
		restController = new QuestionRestController();
		restController.questionService = questionService;
		mvc = MockMvcBuilders.standaloneSetup(restController).setControllerAdvice(new QuestionExceptionHandler())
				.build();
	}

	@Test
	void testAll() {

		ResponseEntity<List<QuestionDto>> responseEntity = restController.all();
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testGetOneQuestion() {
		ResponseEntity<QuestionDto> responseEntity = restController.oneQuestion(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	void addQuestion() {

		ResponseEntity<QuestionDto> responseEntity = restController.newQuestion(qDto1);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
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

//	@Test
//	void testGetOneQuestionForNotFoundException() throws Exception {
////		when(questionService.getQuestion(anyInt())).thenAnswer((w) -> {
////			throw new InValidQuestionId(Constants.INVALID_QUESTION_ID);
////		});
////		int temp = -111;
////		when(questionService.getQuestion(anyInt())).thenThrow(new InValidQuestionId(Constants.INVALID_QUESTION_ID));
//		int temp = -111;
////		ResponseEntity<QuestionDto> responseEntity = restController.oneQuestion(temp);
////		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//		when(questionService.getQuestion(anyInt())).thenAnswer(w -> throw new Exception());
//		mvc.perform(get("/questions/-111")).andDo(print()).andExpect(status().isBadRequest());
//	}
}
