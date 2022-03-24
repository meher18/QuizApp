package com.epam.service.libraryservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.data.repository.UserQuizRepository;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@Service

public class UserQuizLibraryService {

	@Autowired
	UserQuizRepository userQuizRepository;

	public Map<User, UserQuiz> getUserQuizMap() {
		List<UserQuiz> userQuizList = (List<UserQuiz>) userQuizRepository.findAll();
		return userQuizList.stream().collect(Collectors.toMap(UserQuiz::getUser, v -> v));
	}

	public Map<Integer, UserQuiz> getUserQuizzesWithId() {
		List<UserQuiz> userQuizList = (List<UserQuiz>) userQuizRepository.findAll();
		return userQuizList.stream()
				.collect(Collectors.toMap(UserQuiz::getQuizId, v -> v));
	}

	public boolean addQuiz(UserQuiz quiz) {
		userQuizRepository.save(quiz);
		return true;
	}

	public boolean editQuiz(UserQuiz quiz) {
		userQuizRepository.save(quiz);
		return true;
	}


}
