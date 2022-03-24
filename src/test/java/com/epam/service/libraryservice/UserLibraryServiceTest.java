package com.epam.service.libraryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.data.repository.UserRepository;
import com.epam.entity.User;

@ExtendWith(MockitoExtension.class)
class UserLibraryServiceTest {

	@Mock
	UserLibraryService libraryService;

	@Mock
	UserRepository userRepository;

	User user = new User();
	@BeforeEach
	void init() {
		libraryService = new UserLibraryService();
		libraryService.userRepository = userRepository;
	}

	@Test
	void testAddUser() {
		when(userRepository.save(any())).thenReturn(user);
		assertTrue(libraryService.addUser(user));
	}

	@Test
	void testEditUser() {
		when(userRepository.save(any())).thenReturn(user);
		assertTrue(libraryService.editUser(user));
	
	}

	@Test
	void testDeleteUser() {
		assertTrue(libraryService.deleteUser(1));
		verify(userRepository, times(1)).deleteById(any());
	
	}

	@Test
	void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(new User[] {user}));
		assertEquals(1, libraryService.getAllUsers().size());
	
	}

}
