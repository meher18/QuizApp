package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.data.repository.QuestionRepository;
import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;

@Service
public class QuestionsLibraryService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;

	public Question getQuestion(int questionId) {

		return questionRepository.findById(questionId).get();
	}

	public Map<Integer, Question> getQuestions() {
		List<Question> questionsList = (List<Question>) questionRepository.findAll();
		Map<Integer, Question> questions = questionsList.stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
		return questions;
	}

	public boolean addQuestion(int index, Question question) {
		boolean isQuestionValid = false;

		Question questionSaved = questionRepository.save(question);
		if (questionSaved != null) {
			isQuestionValid = true;
		}
		return isQuestionValid;
	}

	public boolean editQuestion(int index, Question newQuestion) {
		boolean isQuestionValid = false;

		Question questionSaved = questionRepository.save(newQuestion);
		if (questionSaved != null) {
			isQuestionValid = true;
		}
		return isQuestionValid;
	}

	public boolean deleteQuestion(int index) {
		questionRepository.deleteById(index);
		return true;
	}

	public int getNoQuizzesForQuestionId(int questionId) {
		AtomicInteger count = new AtomicInteger();

		count.set(0);
		quizRepository.findAll().forEach(quiz -> {
			if (quiz.getQuestions().containsKey(questionId)) {
				count.getAndIncrement();
			}

		});
		return count.get();
	}
}
