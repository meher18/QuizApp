package com.epam.service.libraryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.data.repository.UserQuizRepository;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@ExtendWith(MockitoExtension.class)
class UserQuizLibraryServiceTest {

	@Mock
	UserQuizLibraryService libraryService;

	@Mock
	UserQuizRepository userQuizRepository;

	Map<Integer, UserQuiz> userQuizzes = new HashMap<>();

	UserQuiz userQuiz1 = new UserQuiz();
	UserQuiz userQuiz2 = new UserQuiz();

	@BeforeEach
	void init() {

		userQuiz1.setQuizId(1);
		userQuiz2.setQuizId(2);
		userQuiz1.setUser(new User());
		userQuiz2.setUser(new User());

		userQuizzes.put(1, userQuiz1);
		userQuizzes.put(2, userQuiz2);
		libraryService = new UserQuizLibraryService();
		libraryService.userQuizRepository = userQuizRepository;

	}

	@Test
	void testGetUserQuizMap() {
		when(userQuizRepository.findAll()).thenReturn(new ArrayList<>(userQuizzes.values()));
		assertEquals(2, libraryService.getUserQuizMap().size());
	}

	@Test
	void testGetUserQuizzesWithId() {
		when(userQuizRepository.findAll()).thenReturn(new ArrayList<>(userQuizzes.values()));
		assertEquals(2, libraryService.getUserQuizzesWithId().size());
	}

	@Test
	void testAddQuiz() {
		assertTrue(libraryService.addQuiz(new User(), userQuiz1));
		verify(userQuizRepository).save(any());
	}

	@Test
	void testEditQuiz() {
		assertTrue(libraryService.editQuiz(new User(), userQuiz1));
		verify(userQuizRepository).save(any());
	}


}
