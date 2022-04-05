package com.epam.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

	public List<Quiz> findByQuestionsId(int questionId);
}
