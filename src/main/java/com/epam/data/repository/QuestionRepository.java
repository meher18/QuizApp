package com.epam.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
	
	@Query(nativeQuery = true, value = "select count(*) from quiz_questions where questions_id = ?1")
	public int getNoOfQuizzesForQuestion(int questionId);
	
}
