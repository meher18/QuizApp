package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionRepository;
import com.epam.entity.Question;

@Component
public class QuestionsLibraryService {

	@Autowired
	QuestionRepository questionRepository;

	public Question getQuestion(int questionId) throws NoSuchElementException {

		return questionRepository.findById(questionId).get();
	}

	public Map<Integer, Question> getQuestions() {
		List<Question> questionsList = (List<Question>) questionRepository.findAll();
		return questionsList.stream().collect(Collectors.toMap(Question::getId, v -> v));
	}

	public Question saveOrEdit(Question question) {
		return questionRepository.save(question);
	}

	public boolean deleteQuestion(int index) {
		questionRepository.deleteById(index);
		return true;
	}

	public int getNoQuizzesForQuestionId(int questionId) {

		return questionRepository.getNoOfQuizzesForQuestion(questionId);
	}
}
