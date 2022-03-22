package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.entity.User;
import com.epam.service.user.UserSignInService;
import com.epam.service.user.UserSignUpService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
 class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserSignInService signInService;

	@MockBean
	UserSignUpService signUpService;

	@Test
	void userSignInTestForValidSingIn() throws Exception {

		User user = new User();
		user.setUserName("msr");
		user.setUserPassword("123");
		when(signInService.validate(any())).thenReturn(user);
		mockMvc.perform(get("/signInTheUser").param("userName", user.getUserName()).param("userPassword",
				user.getUserPassword())).andExpect(view().name("redirect:/userModule"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	void userSignInTestForInValidSingIn() throws Exception {

		User user = new User();
		user.setUserName("msr");
		user.setUserPassword("43");
		when(signInService.validate(any())).thenReturn(null);
		mockMvc.perform(get("/signInTheUser").param("userName", user.getUserName()).param("userPassword",
				user.getUserPassword())).andExpect(view().name("redirect:/userSignIn"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	void userSignUpForValidNewUser() throws Exception {

		User user = new User();
		user.setUserName("msr");
		user.setEmail("msr@gmail.com");
		user.setUserPassword("43");
		when(signUpService.checkIfAlreadyMember(any())).thenReturn(false);
		mockMvc.perform(get("/signUpTheUser").param("userName", user.getUserName()).param("userEmail", user.getEmail())
				.param("userPassword", user.getUserPassword())).andExpect(view().name("redirect:/userModule"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	void userSignUpForAlreadyAUser() throws Exception {

		User user = new User();
		user.setUserName("msr");
		user.setEmail("msr@gmail.com");
		user.setUserPassword("43");
		when(signUpService.checkIfAlreadyMember(any())).thenReturn(true);
		mockMvc.perform(get("/signUpTheUser").param("userName", user.getUserName()).param("userEmail", user.getEmail())
				.param("userPassword", user.getUserPassword())).andExpect(view().name("redirect:/userSignUp"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	void logoutUserTest() throws Exception {
		
		User user = new User();
		user.setUserName("msr");
		user.setEmail("email");
		
		mockMvc.perform(get("/userLogout")
				.sessionAttr("userName", user.getUserName())
				.sessionAttr("userEmail", user.getEmail()))
		.andExpect(view().name("redirect:/"));
	}
}
