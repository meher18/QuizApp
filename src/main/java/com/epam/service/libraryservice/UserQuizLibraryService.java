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
		Map<User, UserQuiz> userQuizzes = userQuizList.stream().collect(Collectors.toMap(k -> k.getUser(), v -> v));

		return userQuizzes;
	}

	public Map<Integer, UserQuiz> getUserQuizzesWithId() {
		List<UserQuiz> userQuizList = (List<UserQuiz>) userQuizRepository.findAll();
		Map<Integer, UserQuiz> userQuizzes = userQuizList.stream()
				.collect(Collectors.toMap(k -> k.getQuizId(), v -> v));

		return userQuizzes;
	}

	public boolean addQuiz(User user, UserQuiz quiz) {
		userQuizRepository.save(quiz);
		return true;
	}

	public boolean editQuiz(User user, UserQuiz quiz) {
		userQuizRepository.save(quiz);
		return true;
	}


}
