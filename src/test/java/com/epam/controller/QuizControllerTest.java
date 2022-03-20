package com.epam.controller;

import static org.hamcrest.Matchers.hasSize;
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

import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.admin.QuestionService;
import com.epam.service.admin.QuizService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(QuizController.class)
public class QuizControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private QuestionService questionService;
	
	@MockBean
	private QuizService quizService;
	
	
	Map<Integer, Question> questions = new HashMap<>();
	Question q1 = new Question();
	Question q2 = new Question();
	
	
	Map<Integer, Quiz> quizzes = new HashMap<>();
	Quiz quiz1 = new Quiz();
	Quiz quiz2 = new Quiz();

	String id;
	String quizName;
	String questionId;
	String quizTag;
	
	
	@BeforeEach
	void setUp()
	{
		q1.setId(1);
		q2.setId(2);
		questions.put(1, q1);
		questions.put(2, q2);
		
		quiz1.addQuestion(1, q1);
		quiz1.addQuestion(2, q2);
		quizzes.put(1, quiz1);
		quizzes.put(2, quiz2);
	}

	@Test
	void viewAllQuizzes() throws Exception {

		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/viewQuizzes"))
		.andExpect(view().name("admin/quiz/viewQuizzes"))
		.andExpect(model().attribute("quizzes", hasSize(2)))
		.andExpect(status().isOk());

	}
	
	@Test
	void updateQuiz() throws Exception {
		
		id = "1";
		when(quizService.getQuiz(1)).thenReturn(quiz1);
		when(questionService.getQuestions()).thenReturn(questions);
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/updateQuiz").param("id", id))
		.andExpect(model().attribute("quiz", quiz1))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(model().attribute("ids", hasSize(2)))
		.andExpect(view().name("admin/quiz/updateQuiz"))
		.andExpect(status().isOk());
	}
	
	@Test
	void createQuiz() throws Exception{
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/createQuiz"))
		.andExpect(view().name("admin/quiz/createQuiz"))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(status().isOk());

	}
	
	@Test
	void hostTheQuiz() throws Exception{
		
		id = "1";
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/hostTheQuiz").param("id", id))
		.andExpect(view().name("redirect:/viewQuizzes"))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void deleteTheQuiz() throws Exception{
		
		id = "1";
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/deleteTheQuiz").param("id", id))
		.andExpect(view().name("redirect:/viewQuizzes"))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void createTheQuizWithNoErrors() throws Exception{
		
		quizName = "quiz";
		questionId = "1,2";
		
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/createTheQuiz")
				.param("quizName", quizName)
				.param("questionId", questionId))
		.andExpect(view().name("redirect:/viewQuizzes"))
		.andExpect(status().is3xxRedirection());
		
		// model attribute is not used in case of redirect
	}
	
	@Test
	void createTheQuizWithErrors() throws Exception{
		
		quizName = "";
		questionId = "";
		
		when(questionService.getQuestions()).thenReturn(questions);
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/createTheQuiz")
				.param("quizName", quizName)
				.param("questionId", questionId))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(view().name("admin/quiz/createQuiz"))
		.andExpect(status().isOk());
		
		// model attribute is not used in case of redirect
	}
	
	@Test
	void updateTheQuizWithNoErrors() throws Exception{
		
		id = "1";
		quizName = "quiz";
		questionId = "1,2,3,4,5,6,7,8,9,10,11,12,13";
		quizTag = "";
		
		when(quizService.getQuiz(1)).thenReturn(quiz1);
		when(questionService.getQuestions()).thenReturn(questions);
		when(quizService.getAllQuizzes()).thenReturn(quizzes);
		mockMvc.perform(get("/updateTheQuiz")
				.param("id", id)
				.param("quizName", quizName)
				.param("questionId", questionId)
				.param("quizTag", quizTag))
//		.andExpect(view().name("redirect:/updateQuiz?id="+id))
		.andExpect(status().is3xxRedirection());
		
	}
	@Test
	void updateTheQuizWithErrors() throws Exception{
		id = "1";
		quizName = "";
		questionId = "";
		quizTag = "";
		
		when(questionService.getQuestions()).thenReturn(questions);
		when(quizService.getQuiz(1)).thenReturn(quiz1);
		mockMvc.perform(get("/updateTheQuiz")
				.param("id", id)
				.param("quizName", quizName)
				.param("questionId", questionId)
				.param("quizTag", quizTag))
		.andExpect(view().name("redirect:/updateQuiz?id="+id))
		.andExpect(status().is3xxRedirection());
	}
}
