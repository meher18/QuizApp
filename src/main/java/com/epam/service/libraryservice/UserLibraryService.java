package com.epam.service.libraryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.data.repository.UserRepository;
import com.epam.entity.User;

@Service

public class UserLibraryService {

	@Autowired
	UserRepository userRepository;

    public boolean addUser(User user) {
    	userRepository.save(user);
        return true;
    }

    public boolean editUser(User user) {
    	userRepository.save(user);
        return true;
    }

    public boolean deleteUser(int userId) {
    	userRepository.deleteById(userId);
        return true;
    }

    public List<User> getAllUsers() {
    	return (List<User>) userRepository.findAll();
    }
}
