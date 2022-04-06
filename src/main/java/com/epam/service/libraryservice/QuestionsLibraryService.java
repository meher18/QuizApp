package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionRepository;
import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.exceptions.InValidQuestionId;
import com.epam.util.Constants;

@Component
public class QuestionsLibraryService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;

	public Question getQuestion(int questionId) {

		return questionRepository.findById(questionId)
				.orElseThrow(() -> new InValidQuestionId(Constants.INVALID_QUESTION_ID));
	}

	public Map<Integer, Question> getQuestions() {
		List<Question> questionsList = (List<Question>) questionRepository.findAll();
		return questionsList.stream().collect(Collectors.toMap(Question::getId, v -> v));
	}

	public Question addQuestion(Question question) {
		return questionRepository.save(question);
	}

	public Question editQuestion(Question newQuestion) {
		return questionRepository.save(newQuestion);

	}

	public void deleteQuestion(int id) {
		getQuestion(id);
		questionRepository.deleteById(id);

	}

	public int getNoQuizzesForQuestionId(int questionId) {

		return questionRepository.getNoOfQuizzesForQuestion(questionId);
	}
}
