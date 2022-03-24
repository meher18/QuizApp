package com.epam.service.libraryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.data.repository.QuestionRepository;
import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.entity.Quiz;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class QuestionsLibraryServiceTest {

	@Mock
	private QuestionsLibraryService libraryService;

	@MockBean
	private QuestionRepository questionRepository;

	@MockBean
	private QuizRepository quizRepository;

	Map<Integer, Question> questions = new HashMap<>();
	Question q1 = new Question();
	Question q2 = new Question();

	Map<Integer, Quiz> quizzes = new HashMap<>();
	Quiz quiz1 = new Quiz();
	Quiz quiz2 = new Quiz();
	QuestionOption questionOption = new QuestionOption();

	@BeforeEach
	void init() {
		questionOption.setId(0);
		questionOption.setTitle("asdfasdf");

		q1.setId(1);
		q1.setOption(questionOption);

		q2.setId(2);
		q2.setOption(questionOption);

		quiz1.addQuestion(1, q1);
		quiz2.addQuestion(1, q2);

		questions.put(1, q1);
		questions.put(2, q2);

		libraryService = new QuestionsLibraryService();
		libraryService.questionRepository = questionRepository;
		
	}

	@Test
	void testGetQuestion() {

		Question question = new Question();
		question.setId(1);
		question.setQuestionTitle("adsfasdf");
		when(questionRepository.findById(anyInt())).thenReturn(Optional.ofNullable(question));
		assertNotNull(libraryService.getQuestion(1));
	}

	@Test
	void testGetQuestions() {
		when(questionRepository.findAll()).thenReturn(new ArrayList<>(questions.values()));
		assertEquals(2, libraryService.getQuestions().size());
	}

	@Test
	void testAddQuestion() {
		when(questionRepository.save(any())).thenReturn(q1);
		assertNotNull(libraryService.saveOrEdit(q1));
	}

	@Test
	void testEditQuestion() {
		when(questionRepository.save(any())).thenReturn(q1);
		assertNotNull(libraryService.saveOrEdit(q1));
	}

	@Test
	void testDeleteQuestion() {
		assertTrue(libraryService.deleteQuestion(0));
		verify(questionRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void testGetNoQuizzesForQuestionId() {
		when(questionRepository.findAll()).thenReturn(new ArrayList<>(questions.values()));
		when(quizRepository.findAll()).thenReturn(new ArrayList<>(quizzes.values()));
		assertEquals(0, libraryService.getNoQuizzesForQuestionId(1));
	}
}
