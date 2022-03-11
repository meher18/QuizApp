package com.epam.service.admin.quizservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.library.QuestionsLibrary;
import com.epam.data.library.QuizLibrary;
import com.epam.entity.Question;
import com.epam.entity.Quiz;
import com.epam.exceptions.InValidQuizId;
import com.epam.util.Constants;

@Component
public class QuizService {

	@Autowired
	QuizLibrary quizLibrary;

	@Autowired
	QuestionsLibrary questionsLibrary;

	public Map<Integer, Quiz> getAllQuizzes() {

		return quizLibrary.getQuizzes();
	}

	public Map<Integer, Question> getQuestionForQuiz(Quiz quiz) {
		return quizLibrary.getQuestionsForQuiz(quiz);
	}

	public Quiz getQuiz(int quizId) {
		return quizLibrary.getQuiz(quizId);
	}

	public boolean selectQuestionAndAddToQuiz(Quiz quiz, int questionId) {

		boolean isQuestionAdded = false;

		Question question = questionsLibrary.getQuestions().get(questionId);

		if (question != null) {
			quiz.addQuestion(questionId, question);
			isQuestionAdded = true;
		}
		return isQuestionAdded;
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

//    public boolean validateQuizCode(int quizId) {
//        QuizLibrary quizLibrary = AppContext.getApplicationContext().getBean(QuizLibrary.class);
//        return quizLibrary.getQuizzes().containsKey(quizId);
//    }

	public boolean delete(int quizId) {

		boolean isQuizDeleted = false;
		if (quizId > 0) {
			isQuizDeleted = quizLibrary.deleteQuiz(quizId);
		}
		return isQuizDeleted;
	}

	public boolean hostQuiz(int quizId) {

		boolean isHosted = false;
		if (quizId > 0 && quizLibrary.getQuizzes().size() > 0) {
			isHosted = quizLibrary.changeQuizStatus(quizId, Constants.QUIZ_HOSTED);
		}
		return isHosted;
	}

	public boolean update(Quiz quiz, int quizId) {

		boolean isQuizModified = false;
		if (quizLibrary.getQuizzes().size() > 0) {
			isQuizModified = quizLibrary.editQuiz(quizId, quiz);
		}
		return isQuizModified;
	}

}
