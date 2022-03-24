package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

		return quizList.stream().collect(Collectors.toMap(Quiz::getId, v -> v));
	}

	public Quiz getQuiz(int quizId) throws NoSuchElementException {
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		return quiz.get();
	}
	public Quiz saveOrEdit(Quiz quiz) {
		
		return quizRepository.save(quiz);
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
		
	// get questions for certain quiz
	public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {
		Optional<Quiz> quizFromRepo = quizRepository.findById(quiz.getId());
		return quizFromRepo.get().getQuestions();
	}
}
