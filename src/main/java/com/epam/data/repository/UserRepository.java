package com.epam.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
