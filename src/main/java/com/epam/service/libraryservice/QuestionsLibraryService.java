package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuestionRepository;
import com.epam.data.repository.QuizRepository;
import com.epam.entity.Question;
import com.epam.exceptions.QuestionNotFoundException;

@Component
public class QuestionsLibraryService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;
	
	public Question getQuestion(int questionId) {

		return questionRepository.findById(questionId)
				.orElseThrow(() -> new QuestionNotFoundException("Question with id " + questionId + " not found"));
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

		return quizRepository.findByQuestionsId(questionId).size();
	}
}
