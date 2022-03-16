package com.epam.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
	
	
}
