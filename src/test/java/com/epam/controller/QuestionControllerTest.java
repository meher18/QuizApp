package com.epam.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
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

import com.epam.dto.QuestionDto;
import com.epam.entity.Question;
import com.epam.service.admin.QuestionService;
import com.epam.service.libraryservice.QuestionsLibraryService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuestionService questionService;

	@MockBean
	private QuestionsLibraryService library;

	Map<Integer, QuestionDto> questions = new HashMap<>();
	QuestionDto q1 = new QuestionDto();
	QuestionDto q2 = new QuestionDto();

	Map<Integer, Question> questionsMap = new HashMap<>();
	Question quesiton1 = new Question();
	Question question2 = new Question();

	String title;
	String options;
	String optionsVal;
	String topic;
	String difficulty;
	String answer;
	String mark;

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
	void addQuestionTestForNoErrors() throws Exception {
		title = "questionTitle";

		optionsVal = Arrays.asList(new String[] { "asdf", "asdfasdf" }).toString();
		topic = "java";
		difficulty = "easy";
		answer = "2";
		mark = "10";

		mockMvc.perform(get("/addQuestion").param("id", "0").param("questionTitle", title)
				.param("questionOptions", optionsVal).param("topicTag", topic).param("difficultyTag", difficulty)
				.param("answer", answer).param("mark", mark)).andExpect(view().name("redirect:/viewQuestions"))
				.andExpect(status().is3xxRedirection());

	}

	@Test
	void addQuestionTestForErrors() throws Exception {

		mockMvc.perform(get("/addQuestion").param("id", "").param("questionTitle", "").param("questionOptions", "")
				.param("topicTag", "").param("difficultyTag", difficulty).param("answer", answer).param("mark", mark))
				.andExpect(view().name("admin/question/createQuestion")).andExpect(status().isOk());
	}

	@Test
	void updateQuestionForNoErrors() throws Exception {
		title = "questionTitle";
		optionsVal = "1,3";
		topic = "java";
		difficulty = "easy";
		answer = "2";
		mark = "10";

		when(questionService.getQuestion(1)).thenReturn(q1);
		mockMvc.perform(get("/updateTheQuestion").param("id", "0").param("questionTitle", title)
				.param("questionOptions", optionsVal).param("topicTag", topic).param("difficultyTag", difficulty)
				.param("answer", answer).param("mark", mark)).andExpect(view().name("redirect:/viewQuestions"))
				.andExpect(status().is3xxRedirection());

	}

	@Test
	void updateQuestionForErrors() throws Exception {
		String id = "1";
		title = "";
		optionsVal = "";
		topic = "";
		difficulty = "";
		answer = "";
		mark = "";

		when(questionService.getQuestion(1)).thenReturn(q1);
		mockMvc.perform(get("/updateTheQuestion").param("id", id).param("title", title).param("optionsVal", optionsVal)
				.param("topic", topic).param("difficulty", difficulty).param("answer", answer).param("mark", mark))
				.andExpect(view().name("redirect:/updateQuestion?id=" + id)).andExpect(status().is3xxRedirection());

	}

	@Test
	void deletionForValidQuestionId() throws Exception {
		String id = "1";
		when(questionService.getQuestions()).thenReturn(questions);
		when(questionService.delete(1)).thenReturn(true);
		mockMvc.perform(get("/deleteTheQuestion").param("id", id))
				.andExpect(view().name("redirect:/viewQuestions")).andExpect(status().is3xxRedirection());

	}

	@Test
	void deleteForInvalidQuestionId() throws Exception {

		String id = "111";
//		when(library.getNoQuizzesForQuestionId(-1)).thenReturn()
		when(library.getQuestions()).thenReturn(questionsMap);
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/deleteTheQuestion").param("id", id))
				.andExpect(view().name("redirect:/viewQuestions")).andExpect(status().is3xxRedirection());
	}

	@Test
	void deleteForQuestionIdPresentInQuiz() throws Exception {

		String id = "-1";
		when(library.getNoQuizzesForQuestionId(-1)).thenReturn(10);
		when(questionService.getQuestions()).thenReturn(questions);
		mockMvc.perform(get("/deleteTheQuestion").param("id", id))
				.andExpect(view().name("redirect:/viewQuestions")).andExpect(status().is3xxRedirection());
	}
}
