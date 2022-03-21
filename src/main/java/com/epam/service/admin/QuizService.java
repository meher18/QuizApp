package com.epam.service.admin;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuizDto;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.exceptions.InValidQuizId;
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

	public Map<Integer, Question> getQuestionForQuiz(Quiz quiz) {
		return quizLibrary.getQuestionsForQuiz(quiz);
	}

	public QuizDto getQuiz(int quizId) {
		Quiz quiz = quizLibrary.getQuiz(quizId);
		QuizDto quizDto = mapper.map(quiz, QuizDto.class);
		quizDto.setQuestions(quiz.getQuestions().values().stream().collect(Collectors.toList()));
		return quizDto;
	}

	public boolean createQuiz(QuizDto quizDto, String[] questions) {
		Quiz quiz = new Quiz();
		quiz.setQuizName(quizDto.quizName);
		Stream.of(questions).forEach(id -> selectQuestionAndAddToQuiz(quiz, Integer.parseInt(id)));
		saveQuiz(quiz);
		return true;
	}

	public void selectQuestionAndAddToQuiz(Quiz quiz, int questionId) {

		Question question = questionsLibrary.getQuestions().get(questionId);

		if (question != null) {
			quiz.addQuestion(questionId, question);

		}
	}

	public boolean saveQuiz(Quiz quiz) {

		boolean isQuizSaved = false;
		int quizId = 0;
		// isAdded : to check if the quiz is added correctly
		boolean isAdded = quizLibrary.addQuiz(quizId, quiz);
		if (isAdded) {
			isQuizSaved = true;
		}
		return isQuizSaved;
	}

	public void validateCode(int quizId) throws InValidQuizId {

		if (quizLibrary.getQuiz(quizId) == null || quizId <= 0) {

			throw new InValidQuizId(Constants.INVALID_QUIZ_ID);

		}
	}

	public boolean delete(int quizId) {

	
		return  quizLibrary.deleteQuiz(quizId);

	
	}

	public boolean hostQuiz(int quizId) {

		boolean isHosted = false;
		if (quizId > 0 && quizLibrary.getQuizzes().size() > 0) {
			isHosted = quizLibrary.changeQuizStatus(quizId, Constants.QUIZ_HOSTED);
		}
		return isHosted;
	}

	public boolean update(QuizDto quizDto, String[] questionIds) {

		int quizId = quizDto.id;
		Quiz quiz = quizLibrary.getQuiz(quizId);
		quiz.setQuizName(quizDto.quizName);

		quiz.getQuestions().clear();
		quiz.setTotalMarks(0);
		Stream.of(questionIds).forEach(id -> {
			selectQuestionAndAddToQuiz(quiz, Integer.parseInt(id));
		});

		quiz.setQuizTag(quizDto.quizTag);
		boolean isQuizModified = false;
		if (quizLibrary.getQuizzes().size() > 0) {
			isQuizModified = quizLibrary.editQuiz(quizId, quiz);
		}
		return isQuizModified;
	}

}
