package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.exceptions.InValidQuizId;
import com.epam.util.Constants;

@Service
public class QuizLibraryService {

	// the dependency should not be static
	// for the mockito

	@Autowired
	QuizRepository quizRepository;

	public Map<Integer, Quiz> getQuizzes() {
		List<Quiz> quizList = (List<Quiz>) quizRepository.findAll();
		return quizList.stream().collect(Collectors.toMap(Quiz::getId, v -> v));
	}

	public Quiz getQuiz(int quizId) {
		return quizRepository.findById(quizId).orElseThrow(() -> new InValidQuizId(Constants.INVALID_QUIZ_ID));
	}

	public Quiz addQuiz(Quiz quiz) {

		return quizRepository.save(quiz);
	}

	public Quiz editQuiz(Quiz quiz) {

		return quizRepository.save(quiz);
	}

	public void deleteQuiz(int id) {
		getQuiz(id);
		quizRepository.deleteById(id);
	}

	public boolean changeQuizStatus(int id, String status) {
		Quiz quiz = getQuiz(id);
		quiz.setQuizTag(status);
		quizRepository.save(quiz);
		return true;
	}

	// get questions for certain quiz
	public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {
		Quiz quizFromRepo = getQuiz(quiz.getId());
		return quizFromRepo.getQuestions();
	}
}
