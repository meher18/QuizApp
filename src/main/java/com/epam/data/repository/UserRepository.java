package com.epam.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	public Optional<User> findByUserName(String username);
}
