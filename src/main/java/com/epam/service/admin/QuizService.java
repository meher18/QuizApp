package com.epam.service.admin;

import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.service.libraryservice.QuestionsLibraryService;
import com.epam.service.libraryservice.QuizLibraryService;
import com.epam.util.Constants;

@Component
public class QuizService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	QuizLibraryService quizLibrary;

	@Autowired
	QuestionsLibraryService questionsLibrary;

	public Map<Integer, QuizDto> getAllQuizzes() {

		return quizLibrary.getQuizzes().values().stream().collect(Collectors.toMap(Quiz::getId, value -> {
			QuizDto quizDto = mapper.map(value, QuizDto.class);
			quizDto.setQuestions(value.getQuestions().values().stream().toList());
			return quizDto;
		}));
	}

	public Map<Integer, QuestionDto> getQuestionsForQuiz(Quiz quiz) {
		return quizLibrary.getQuestionsForQuiz(quiz).values().stream()
				.collect(Collectors.toMap(Question::getId, value -> mapper.map(value, QuestionDto.class)));
	}

	public QuizDto getQuiz(int quizId) {
		Quiz quiz = quizLibrary.getQuiz(quizId);
		QuizDto quizDto = mapper.map(quiz, QuizDto.class);
		quizDto.setQuestions(quiz.getQuestions().values().stream().collect(Collectors.toList()));
		return quizDto;
	}

	// making list of question to map of question
	public void selectQuestionAndAddToQuiz(Quiz quiz, int questionId) {
		quiz.addQuestion(questionId, questionsLibrary.getQuestion(questionId));
	}
	
	public QuizDto saveQuiz(Quiz quiz) {

		Quiz savedQuiz = quizLibrary.saveOrEdit(quiz);
		QuizDto savedQuizDto = mapper.map(savedQuiz, QuizDto.class);
		savedQuizDto.setQuestions(savedQuiz.getQuestions().values().stream().collect(Collectors.toList()));
		return savedQuizDto;

	}

	public QuizDto createQuiz(QuizDto quizDto) {
		Quiz quiz = mapper.map(quizDto, Quiz.class);
		quizDto.getQuestions().stream().forEach(question -> selectQuestionAndAddToQuiz(quiz, question.getId()));
		return saveQuiz(quiz);
	}

	public QuizDto hostQuiz (int quizId)
	{
		Quiz quizToBeHosted = quizLibrary.getQuiz(quizId);
		quizToBeHosted.setQuizTag(Constants.QUIZ_HOSTED);
		return saveQuiz(quizToBeHosted);
	}

	public boolean delete(int quizId) {

		return quizLibrary.deleteQuiz(quizId);

	}

	public QuizDto update(QuizDto quizDto) {

		int quizId = quizDto.id;
		Quiz quiz = quizLibrary.getQuiz(quizId);
		quiz.setQuizName(quizDto.quizName);

		quiz.getQuestions().clear();
		quiz.setTotalMarks(0);
		quizDto.getQuestions().stream().forEach(question -> {
			selectQuestionAndAddToQuiz(quiz, question.getId());
		});
		quiz.setQuizTag(quizDto.quizTag);
		return saveQuiz(quiz);
	}

}
