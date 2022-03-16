package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Service
public class QuizLibraryService {

	// the dependency should not be static
	// for the mockito

	@Autowired
	QuizRepository quizRepository;

	public Map<Integer, Quiz> getQuizzes() {
		List<Quiz> quizList = (List<Quiz>) quizRepository.findAll();

		Map<Integer, Quiz> quizzes = quizList.stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
		return quizzes;
	}

	public boolean addQuiz(int index, Quiz quiz) {
		quizRepository.save(quiz);
		return true;
	}

	public boolean editQuiz(int index, Quiz quiz) {
		quizRepository.save(quiz);
		return true;
	}

	public boolean deleteQuiz(int index) {
		quizRepository.deleteById(index);
		return true;
	}

	public boolean changeQuizStatus(int quizCode, String status) {
		Optional<Quiz> quiz = quizRepository.findById(quizCode);
		quiz.get().setQuizTag(status);
		quizRepository.save(quiz.get());
		return true;
	}

	public Quiz getQuiz(int quizId) {
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		return quiz.get();
	}

	public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {

		Optional<Quiz> quizFromRepo = quizRepository.findById(quiz.getId());
		return quizFromRepo.get().getQuestions();
	}
}
