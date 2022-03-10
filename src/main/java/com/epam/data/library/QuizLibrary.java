package com.epam.data.library;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Component
public class QuizLibrary {
	
	// the dependency should not be static
	// for the mockito

	@Autowired
	QuizDao quizDao;

	public Map<Integer, Quiz> getQuizzes() {
		return quizDao.getAllQuiz();
	}

	public boolean addQuiz(int index, Quiz quiz) {
		quizDao.setQuiz(index, quiz);
		return true;
	}

	public boolean editQuiz(int index, Quiz quiz) {
		quizDao.editQuiz(index, quiz);
		return true;
	}

	public boolean deleteQuiz(int index) {
		quizDao.deleteQuiz(index);
		return true;
	}

	public boolean changeQuizStatus(int quizCode, String status) {
		quizDao.changeQuizStatus(quizCode, status);
		return true;
	}

	public Quiz getQuiz(int quizId) {
		return quizDao.getQuiz(quizId);
	}

	public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {

		return quizDao.getQuestionsForQuiz(quiz);
	}
}
