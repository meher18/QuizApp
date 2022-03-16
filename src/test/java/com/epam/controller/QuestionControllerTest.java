package com.epam.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.epam.service.admin.questionservice.QuestionService;
import com.epam.service.libraryservice.QuestionsLibraryService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuestionService questionService;
	
	@MockBean
	private QuestionsLibraryService library;

	Map<Integer, Question> questions = new HashMap<>();
	Question q1 = new Question();
	Question q2 = new Question();

	String title ;
	String options ;
	String optionsVal ;
	String topic ;
	String difficulty;
	String answer;
	String mark ;

	@BeforeEach
	void setUp() {
		
		q1.setId(1);
		q2.setId(2);
		questions.put(1, q1);
		questions.put(2, q2);

	}

	@Test
	void createQuestionView() throws Exception {

		mockMvc.perform(get("/createQuestion")).andExpect(status().isOk())
				.andExpect(view().name("admin/question/createQuestion"));

	}

	@Test
	void viewQuesTest() throws Exception {
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/viewQuestions")).andExpect(status().isOk())
				.andExpect(view().name("admin/question/viewQuestions"))
				.andExpect(model().attribute("questions", hasSize(2)));
		verify(questionService, times(1)).getQuestions();
	}

	@Test
	void updateQuestionViewTest() throws Exception {

		when(questionService.getQuestion(1)).thenReturn(q1);
		String id = "1";
		mockMvc.perform(get("/updateQuestion").param("id", id)).andExpect(status().isOk())
				.andExpect(model().attribute("question", q1)).andExpect(view().name("admin/question/updateQuestion"));
		verify(questionService, times(1)).getQuestion(1);
	}

	@Test
	void addQuestionTestForNoErrors() throws Exception{
		title = "questionTitle";
		options = "2";
		optionsVal = "1,3";
		topic = "java";
		difficulty = "easy";
		answer = "2";
		mark = "10";
		
		mockMvc.perform(get("/addQuestion")
				.param("title", title)
				.param("options", options)
				.param("optionsVal", optionsVal)
				.param("topic", topic)
				.param("difficulty", difficulty)
				.param("answer", answer)
				.param("mark", mark))
		.andExpect(view().name("redirect:/viewQuestions"))
		.andExpect(status().is3xxRedirection());
		
	}
	
	
	@Test
	void addQuestionTestForErrors() throws Exception{
		title = "";
		options = "";
		optionsVal = "";
		topic = "";
		difficulty = "";
		answer = "";
		mark = "";
		
		mockMvc.perform(get("/addQuestion")
				.param("title", title)
				.param("options", options)
				.param("optionsVal", optionsVal)
				.param("topic", topic)
				.param("difficulty", difficulty)
				.param("answer", answer)
				.param("mark", mark))
		.andExpect(view().name("admin/question/createQuestion"))
		.andExpect(status().isOk());
		
	}
	

	@Test
	void updateQuestionForNoErrors() throws Exception{
		String id = "1";
		title = "questionTitle";
		optionsVal = "1,3";
		topic = "java";
		difficulty = "easy";
		answer = "2";
		mark = "10";
		
		when(questionService.getQuestion(1)).thenReturn(q1);
		mockMvc.perform(get("/updateTheQuestion")
				.param("id", id)
				.param("title", title)
				.param("optionsVal", optionsVal)
				.param("topic", topic)
				.param("difficulty", difficulty)
				.param("answer", answer)
				.param("mark", mark))
		.andExpect(view().name("redirect:/viewQuestions"))
		.andExpect(status().is3xxRedirection());
		
	}
	
	

	@Test
	void updateQuestionForErrors() throws Exception{
		String id = "1";
		title = "";
		optionsVal = "";
		topic = "";
		difficulty = "";
		answer = "";
		mark = "";
		
		when(questionService.getQuestion(1)).thenReturn(q1);
		mockMvc.perform(get("/updateTheQuestion")
				.param("id", id)
				.param("title", title)
				.param("optionsVal", optionsVal)
				.param("topic", topic)
				.param("difficulty", difficulty)
				.param("answer", answer)
				.param("mark", mark))
		.andExpect(view().name("redirect:/updateQuestion?id="+ id))
		.andExpect(status().is3xxRedirection());
		
	}
	

	@Test
	void deletionForValidQuestionId() throws Exception{
		String id = "1";
		when(questionService.getQuestions()).thenReturn(questions);
		when(questionService.delete(1)).thenReturn(true);
		mockMvc.perform(get("/deleteTheQuestion")
				.param("id", id))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(model().attribute("deletionStatus", "Deleted"))
		.andExpect(view().name("admin/question/viewQuestions"))
		.andExpect(status().isOk());
		
	}

	@Test
	void deleteForInvalidQuestionId() throws Exception{
		
		String id = "111";
//		when(library.getNoQuizzesForQuestionId(-1)).thenReturn()
		when(library.getQuestions()).thenReturn(questions);
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/deleteTheQuestion")
				.param("id", id))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(model().attribute("deletionStatus", "Not Deleted"))
		.andExpect(view().name("admin/question/viewQuestions"))
		.andExpect(status().isOk());
	}
	
	@Test
	void deleteForQuestionIdPresentInQuiz() throws Exception{
		
		String id = "-1";
		when(library.getNoQuizzesForQuestionId(-1)).thenReturn(10);
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/deleteTheQuestion")
				.param("id", id))
		.andExpect(model().attribute("questions", hasSize(2)))
		.andExpect(model().attribute("deletionStatus", "Not Deleted"))
		.andExpect(view().name("admin/question/viewQuestions"))
		.andExpect(status().isOk());
	}
}
