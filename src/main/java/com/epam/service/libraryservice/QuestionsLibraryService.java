package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionRepository;
import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;

@Component
public class QuestionsLibraryService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;

	public Question getQuestion(int questionId) throws NoSuchElementException {

		return questionRepository.findById(questionId).get();
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

	public boolean deleteQuestion(int index) {
		questionRepository.deleteById(index);
		return true;
	}

	public int getNoQuizzesForQuestionId(int questionId) {
//		AtomicInteger count = new AtomicInteger();
//
//		count.set(0);
//		quizRepository.findAll().forEach(quiz -> {
//			if (quiz.getQuestions().containsKey(questionId)) {
//				count.getAndIncrement();
//			}
//
//		});

		return questionRepository.getNoOfQuizzesForQuestion(questionId);
	}
}
