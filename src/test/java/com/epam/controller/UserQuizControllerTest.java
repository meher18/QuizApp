package com.epam.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.entity.Quiz;
import com.epam.service.admin.QuizService;
import com.epam.service.user.UserQuizService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserQuizContoller.class)
public class UserQuizControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	QuizService quizService;

	@MockBean
	UserQuizService userQuizService;
	
	Map<Integer, Quiz> quizzes = new HashMap<>();
	
	Quiz q1 = new Quiz();
	Quiz q2 = new Quiz();
	
	@BeforeEach
	void setup()
	{
		quizzes.put(1, q1);
		quizzes.put(2, q2);
	}
	
	@Test
	void viewAllHostedQuizzesTest() throws Exception
	{
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/viewHostedQuizzes"))
		.andExpect(view().name("user/viewHostedQuizzes"))
		.andExpect(status().isOk());
	}
	@Test
	void takeTheQuiz() throws Exception
	{
		String id = "1";
		when(quizService.getQuiz(1)).thenReturn(q1);
		mockMvc.perform(get("/takeTheQuiz")
				.param("quizId", id))
		.andExpect(model().attribute("quiz", q1))
		.andExpect(view().name("user/quiz"))
		.andExpect(status().isOk());
	}
}
