package com.epam.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

}
